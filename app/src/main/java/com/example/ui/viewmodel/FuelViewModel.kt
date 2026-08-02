package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.FuelRecord
import com.example.data.repository.FuelRepository
import com.example.ui.model.FuelCalculations
import com.example.ui.model.FuelRecordUiItem
import com.example.ui.model.FuelStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

import com.example.ui.model.AppLanguage
import com.example.util.AppStrings
import com.example.util.ClearAllProtectionValidator
import com.example.util.OdometerValidator

data class FuelUiState(
    val items: List<FuelRecordUiItem> = emptyList(),
    val rawRecords: List<FuelRecord> = emptyList(),
    val stats: FuelStats = FuelStats(),
    val searchQuery: String = "",
    val selectedFuelTypeFilter: String? = null,
    val isLoading: Boolean = false,
    val isAddDialogOpen: Boolean = false,
    val editingRecord: FuelRecord? = null,
    val lastKnownOdometer: Double = 0.0,
    val previousOdometer: Double? = null,
    val isClearAllStep1Open: Boolean = false,
    val isClearAllStep2Open: Boolean = false,
    val clearAllConfirmationText: String = "",
    val snackbarMessage: String? = null,
    val currentLanguage: AppLanguage = AppLanguage.CZ
)

class FuelViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FuelRepository

    private val _searchQuery = MutableStateFlow("")
    private val _fuelTypeFilter = MutableStateFlow<String?>(null)
    private val _isAddDialogOpen = MutableStateFlow(false)
    private val _editingRecord = MutableStateFlow<FuelRecord?>(null)
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    private val _currentLanguage = MutableStateFlow(AppLanguage.CZ)

    private val _isClearAllStep1Open = MutableStateFlow(false)
    private val _isClearAllStep2Open = MutableStateFlow(false)
    private val _clearAllConfirmationText = MutableStateFlow("")

    init {
        val dao = AppDatabase.getDatabase(application).fuelRecordDao()
        repository = FuelRepository(dao)
    }

    private data class FilterState(
        val query: String = "",
        val fuelFilter: String? = null
    )

    private data class DialogState(
        val isAddOpen: Boolean = false,
        val editingRecord: FuelRecord? = null,
        val snackbarMessage: String? = null,
        val language: AppLanguage = AppLanguage.CZ
    )

    private data class DeleteAllState(
        val isStep1Open: Boolean = false,
        val isStep2Open: Boolean = false,
        val confirmationText: String = ""
    )

    private val filterState = combine(_searchQuery, _fuelTypeFilter) { query, filter ->
        FilterState(query, filter)
    }

    private val dialogState = combine(_isAddDialogOpen, _editingRecord, _snackbarMessage, _currentLanguage) { isAddOpen, editingRecord, snackbarMessage, language ->
        DialogState(isAddOpen, editingRecord, snackbarMessage, language)
    }

    private val deleteAllState = combine(_isClearAllStep1Open, _isClearAllStep2Open, _clearAllConfirmationText) { s1, s2, txt ->
        DeleteAllState(s1, s2, txt)
    }

    val uiState: StateFlow<FuelUiState> = combine(
        repository.allRecords,
        filterState,
        dialogState,
        deleteAllState
    ) { rawRecords, filters, dialogs, deleteAll ->

        val filteredRecords = rawRecords.filter { rec ->
            val matchesQuery = filters.query.isBlank() ||
                    rec.stationName.contains(filters.query, ignoreCase = true) ||
                    rec.fuelType.contains(filters.query, ignoreCase = true) ||
                    rec.odometer.toString().contains(filters.query)
            val matchesType = filters.fuelFilter == null || rec.fuelType.equals(filters.fuelFilter, ignoreCase = true)
            matchesQuery && matchesType
        }

        val (uiItems, stats) = FuelCalculations.processRecords(filteredRecords)

        val maxOdometer = rawRecords.maxOfOrNull { it.odometer } ?: 0.0
        val prevOdometer = OdometerValidator.getPreviousOdometer(rawRecords, dialogs.editingRecord)

        FuelUiState(
            items = uiItems,
            rawRecords = rawRecords,
            stats = stats,
            searchQuery = filters.query,
            selectedFuelTypeFilter = filters.fuelFilter,
            isLoading = false,
            isAddDialogOpen = dialogs.isAddOpen,
            editingRecord = dialogs.editingRecord,
            lastKnownOdometer = maxOdometer,
            previousOdometer = prevOdometer,
            isClearAllStep1Open = deleteAll.isStep1Open,
            isClearAllStep2Open = deleteAll.isStep2Open,
            clearAllConfirmationText = deleteAll.confirmationText,
            snackbarMessage = dialogs.snackbarMessage,
            currentLanguage = dialogs.language
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FuelUiState(isLoading = true)
    )

    fun setLanguage(lang: AppLanguage) {
        _currentLanguage.value = lang
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setFuelFilter(filter: String?) {
        _fuelTypeFilter.value = filter
    }

    fun openAddDialog() {
        _editingRecord.value = null
        _isAddDialogOpen.value = true
    }

    fun openEditDialog(record: FuelRecord) {
        _editingRecord.value = record
        _isAddDialogOpen.value = true
    }

    fun closeDialog() {
        _isAddDialogOpen.value = false
        _editingRecord.value = null
    }

    private var isSaving = false

    fun saveRecord(
        date: String,
        odometer: Double,
        litres: Double,
        totalPrice: Double,
        fuelType: String,
        stationName: String
    ) {
        if (isSaving) return

        val prevOdometer = OdometerValidator.getPreviousOdometer(uiState.value.rawRecords, _editingRecord.value)
        val validation = OdometerValidator.validate(odometer, prevOdometer)
        if (validation is OdometerValidator.ValidationResult.Invalid) {
            return
        }

        isSaving = true
        viewModelScope.launch {
            try {
                val recordToSave = _editingRecord.value?.copy(
                    date = date,
                    odometer = odometer,
                    litres = litres,
                    totalPrice = totalPrice,
                    fuelType = fuelType,
                    stationName = stationName
                ) ?: FuelRecord(
                    date = date,
                    odometer = odometer,
                    litres = litres,
                    totalPrice = totalPrice,
                    fuelType = fuelType,
                    stationName = stationName
                )

                val lang = _currentLanguage.value
                if (_editingRecord.value != null) {
                    repository.update(recordToSave)
                    _snackbarMessage.value = AppStrings.recordUpdated(lang)
                } else {
                    repository.insert(recordToSave)
                    _snackbarMessage.value = AppStrings.recordSaved(lang)
                }
                closeDialog()
            } finally {
                isSaving = false
            }
        }
    }

    fun deleteRecord(record: FuelRecord) {
        viewModelScope.launch {
            repository.delete(record)
            _snackbarMessage.value = AppStrings.recordDeleted(_currentLanguage.value)
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }

    fun loadSampleData() {
        viewModelScope.launch {
            val today = LocalDate.now()
            
            // Sample dataset with realistic intervals and litres
            val baseOdometer = 120000.0
            val samples = listOf(
                FuelRecord(
                    date = today.minusDays(30).toString(),
                    odometer = baseOdometer,
                    litres = 42.5,
                    totalPrice = 1615.0, // 38.00 Kč/l
                    fuelType = "Benzín",
                    stationName = "ORLEN Benzina"
                ),
                FuelRecord(
                    date = today.minusDays(23).toString(),
                    odometer = baseOdometer + 580.0,
                    litres = 39.4,
                    totalPrice = 1516.9, // 38.50 Kč/l
                    fuelType = "Benzín",
                    stationName = "Shell"
                ),
                FuelRecord(
                    date = today.minusDays(15).toString(),
                    odometer = baseOdometer + 1190.0,
                    litres = 41.2,
                    totalPrice = 1565.6, // 38.00 Kč/l
                    fuelType = "Benzín",
                    stationName = "MOL"
                ),
                FuelRecord(
                    date = today.minusDays(6).toString(),
                    odometer = baseOdometer + 1820.0,
                    litres = 43.1,
                    totalPrice = 1629.2, // 37.80 Kč/l
                    fuelType = "Benzín",
                    stationName = "OMV"
                )
            )

            for (sample in samples) {
                repository.insert(sample)
            }
            _snackbarMessage.value = AppStrings.sampleDataLoaded(_currentLanguage.value)
        }
    }

    fun openClearAllStep1() {
        _isClearAllStep1Open.value = true
        _isClearAllStep2Open.value = false
        _clearAllConfirmationText.value = ""
    }

    fun proceedToClearAllStep2() {
        _isClearAllStep1Open.value = false
        _isClearAllStep2Open.value = true
        _clearAllConfirmationText.value = ""
    }

    fun updateClearAllConfirmationText(text: String) {
        _clearAllConfirmationText.value = text
    }

    fun cancelClearAll() {
        _isClearAllStep1Open.value = false
        _isClearAllStep2Open.value = false
        _clearAllConfirmationText.value = ""
    }

    fun confirmClearAllData() {
        if (_isClearAllStep2Open.value && ClearAllProtectionValidator.isConfirmationValid(_clearAllConfirmationText.value)) {
            viewModelScope.launch {
                repository.deleteAll()
                _snackbarMessage.value = AppStrings.allDataCleared(_currentLanguage.value)
            }
            cancelClearAll()
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            repository.deleteAll()
            _snackbarMessage.value = AppStrings.allDataCleared(_currentLanguage.value)
        }
    }
}
