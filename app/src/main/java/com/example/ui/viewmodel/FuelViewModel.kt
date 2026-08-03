package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.CarProfile
import com.example.data.model.FuelRecord
import com.example.data.repository.CarProfileRepository
import com.example.data.repository.FuelRepository
import com.example.ui.model.FuelCalculations
import com.example.ui.model.FuelRecordUiItem
import com.example.ui.model.FuelSortOption
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
    val selectedSortOption: FuelSortOption = FuelSortOption.DATE_DESC,
    val isLoading: Boolean = false,
    val isAddDialogOpen: Boolean = false,
    val editingRecord: FuelRecord? = null,
    val lastKnownOdometer: Double = 0.0,
    val previousOdometer: Double? = null,
    val isClearAllStep1Open: Boolean = false,
    val isClearAllStep2Open: Boolean = false,
    val clearAllConfirmationText: String = "",
    val snackbarMessage: String? = null,
    val currentLanguage: AppLanguage = AppLanguage.CZ,
    val carProfile: CarProfile = CarProfile(),
    val isOnboardingDialogOpen: Boolean = false,
    val isCarProfileDialogOpen: Boolean = false,
    val isExportOptionsDialogOpen: Boolean = false,
    val selectedThemePalette: com.example.ui.theme.AppThemePalette = com.example.ui.theme.AppThemePalette.DARK_BLUE,
    val isThemeDialogOpen: Boolean = false
)

class FuelViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FuelRepository
    private val carProfileRepository: CarProfileRepository = CarProfileRepository(application)

    private val _searchQuery = MutableStateFlow("")
    private val _fuelTypeFilter = MutableStateFlow<String?>(null)
    private val _sortOption = MutableStateFlow(FuelSortOption.DATE_DESC)
    private val _isAddDialogOpen = MutableStateFlow(false)
    private val _editingRecord = MutableStateFlow<FuelRecord?>(null)
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    private val _currentLanguage = MutableStateFlow(AppLanguage.CZ)

    private val _isClearAllStep1Open = MutableStateFlow(false)
    private val _isClearAllStep2Open = MutableStateFlow(false)
    private val _clearAllConfirmationText = MutableStateFlow("")

    private val _carProfile = MutableStateFlow(carProfileRepository.getCarProfile())
    private val _isOnboardingDialogOpen = MutableStateFlow(!_carProfile.value.isSetupComplete)
    private val _isCarProfileDialogOpen = MutableStateFlow(false)
    private val _isExportOptionsDialogOpen = MutableStateFlow(false)

    private val _selectedThemePalette = MutableStateFlow(carProfileRepository.getThemePalette())
    private val _isThemeDialogOpen = MutableStateFlow(false)

    init {
        val dao = AppDatabase.getDatabase(application).fuelRecordDao()
        repository = FuelRepository(dao)
    }

    private data class FilterState(
        val query: String = "",
        val fuelFilter: String? = null,
        val sortOption: FuelSortOption = FuelSortOption.DATE_DESC
    )

    private data class DialogState(
        val isAddOpen: Boolean = false,
        val editingRecord: FuelRecord? = null,
        val snackbarMessage: String? = null,
        val language: AppLanguage = AppLanguage.CZ,
        val isExportOptionsOpen: Boolean = false
    )

    private data class DeleteAllState(
        val isStep1Open: Boolean = false,
        val isStep2Open: Boolean = false,
        val confirmationText: String = ""
    )

    private data class CarProfileState(
        val carProfile: CarProfile = CarProfile(),
        val isOnboardingDialogOpen: Boolean = false,
        val isCarProfileDialogOpen: Boolean = false
    )

    private data class ThemeState(
        val palette: com.example.ui.theme.AppThemePalette = com.example.ui.theme.AppThemePalette.DARK_BLUE,
        val isDialogOpen: Boolean = false
    )

    private data class AppSettingsState(
        val carProfile: CarProfile = CarProfile(),
        val isOnboardingDialogOpen: Boolean = false,
        val isCarProfileDialogOpen: Boolean = false,
        val selectedThemePalette: com.example.ui.theme.AppThemePalette = com.example.ui.theme.AppThemePalette.DARK_BLUE,
        val isThemeDialogOpen: Boolean = false
    )

    private val filterState = combine(_searchQuery, _fuelTypeFilter, _sortOption) { query, filter, sort ->
        FilterState(query, filter, sort)
    }

    private val dialogState = combine(_isAddDialogOpen, _editingRecord, _snackbarMessage, _currentLanguage, _isExportOptionsDialogOpen) { isAddOpen, editingRecord, snackbarMessage, language, isExportOpen ->
        DialogState(isAddOpen, editingRecord, snackbarMessage, language, isExportOpen)
    }

    private val deleteAllState = combine(_isClearAllStep1Open, _isClearAllStep2Open, _clearAllConfirmationText) { s1, s2, txt ->
        DeleteAllState(s1, s2, txt)
    }

    private val carProfileState = combine(_carProfile, _isOnboardingDialogOpen, _isCarProfileDialogOpen) { profile, onboardingOpen, profileOpen ->
        CarProfileState(profile, onboardingOpen, profileOpen)
    }

    private val themeState = combine(_selectedThemePalette, _isThemeDialogOpen) { palette, isDialogOpen ->
        ThemeState(palette, isDialogOpen)
    }

    private val appSettingsState = combine(carProfileState, themeState) { carState, theme ->
        AppSettingsState(
            carProfile = carState.carProfile,
            isOnboardingDialogOpen = carState.isOnboardingDialogOpen,
            isCarProfileDialogOpen = carState.isCarProfileDialogOpen,
            selectedThemePalette = theme.palette,
            isThemeDialogOpen = theme.isDialogOpen
        )
    }

    val uiState: StateFlow<FuelUiState> = combine(
        repository.allRecords,
        filterState,
        dialogState,
        deleteAllState,
        appSettingsState
    ) { rawRecords, filters, dialogs, deleteAll, settings ->

        val filteredRecords = rawRecords.filter { rec ->
            val matchesQuery = filters.query.isBlank() ||
                    rec.stationName.contains(filters.query, ignoreCase = true) ||
                    rec.fuelType.contains(filters.query, ignoreCase = true) ||
                    rec.odometer.toString().contains(filters.query)
            val matchesType = filters.fuelFilter == null || rec.fuelType.equals(filters.fuelFilter, ignoreCase = true)
            matchesQuery && matchesType
        }

        val (uiItems, stats) = FuelCalculations.processRecords(filteredRecords, filters.sortOption)

        val maxOdometer = rawRecords.maxOfOrNull { it.odometer } ?: 0.0
        val prevOdometer = OdometerValidator.getPreviousOdometer(rawRecords, dialogs.editingRecord)

        FuelUiState(
            items = uiItems,
            rawRecords = rawRecords,
            stats = stats,
            searchQuery = filters.query,
            selectedFuelTypeFilter = filters.fuelFilter,
            selectedSortOption = filters.sortOption,
            isLoading = false,
            isAddDialogOpen = dialogs.isAddOpen,
            editingRecord = dialogs.editingRecord,
            lastKnownOdometer = maxOdometer,
            previousOdometer = prevOdometer,
            isClearAllStep1Open = deleteAll.isStep1Open,
            isClearAllStep2Open = deleteAll.isStep2Open,
            clearAllConfirmationText = deleteAll.confirmationText,
            snackbarMessage = dialogs.snackbarMessage,
            currentLanguage = dialogs.language,
            carProfile = settings.carProfile,
            isOnboardingDialogOpen = settings.isOnboardingDialogOpen,
            isCarProfileDialogOpen = settings.isCarProfileDialogOpen,
            isExportOptionsDialogOpen = dialogs.isExportOptionsOpen,
            selectedThemePalette = settings.selectedThemePalette,
            isThemeDialogOpen = settings.isThemeDialogOpen
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

    fun setSortOption(sortOption: FuelSortOption) {
        _sortOption.value = sortOption
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
            if (uiState.value.rawRecords.isNotEmpty()) {
                return@launch
            }
            val today = LocalDate.now()
            val defaultFuelType = _carProfile.value.allowedFuelTypes.firstOrNull() ?: "Benzín"
            
            // Sample dataset with at least 10 realistic fuel records
            val baseOdometer = 120000.0
            val samples = listOf(
                FuelRecord(
                    date = today.minusDays(90).toString(),
                    odometer = baseOdometer,
                    litres = 40.0,
                    totalPrice = 1520.0, // 38.00 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "ORLEN Benzina"
                ),
                FuelRecord(
                    date = today.minusDays(81).toString(),
                    odometer = baseOdometer + 560.0,
                    litres = 38.5,
                    totalPrice = 1463.0, // 38.00 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "Shell"
                ),
                FuelRecord(
                    date = today.minusDays(72).toString(),
                    odometer = baseOdometer + 1140.0,
                    litres = 41.0,
                    totalPrice = 1578.5, // 38.50 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "MOL"
                ),
                FuelRecord(
                    date = today.minusDays(63).toString(),
                    odometer = baseOdometer + 1710.0,
                    litres = 39.0,
                    totalPrice = 1501.5, // 38.50 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "OMV"
                ),
                FuelRecord(
                    date = today.minusDays(54).toString(),
                    odometer = baseOdometer + 2280.0,
                    litres = 42.0,
                    totalPrice = 1596.0, // 38.00 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "EuroOil"
                ),
                FuelRecord(
                    date = today.minusDays(45).toString(),
                    odometer = baseOdometer + 2890.0,
                    litres = 43.5,
                    totalPrice = 1644.3, // 37.80 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "ORLEN Benzina"
                ),
                FuelRecord(
                    date = today.minusDays(36).toString(),
                    odometer = baseOdometer + 3470.0,
                    litres = 40.5,
                    totalPrice = 1530.9, // 37.80 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "Shell"
                ),
                FuelRecord(
                    date = today.minusDays(26).toString(),
                    odometer = baseOdometer + 4080.0,
                    litres = 42.0,
                    totalPrice = 1596.0, // 38.00 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "MOL"
                ),
                FuelRecord(
                    date = today.minusDays(15).toString(),
                    odometer = baseOdometer + 4700.0,
                    litres = 44.0,
                    totalPrice = 1672.0, // 38.00 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "OMV"
                ),
                FuelRecord(
                    date = today.minusDays(3).toString(),
                    odometer = baseOdometer + 5320.0,
                    litres = 42.8,
                    totalPrice = 1622.1, // 37.90 Kč/l
                    fuelType = defaultFuelType,
                    stationName = "ORLEN Benzina"
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

    fun saveCarProfile(carName: String, spz: String, allowedFuelTypes: List<String>) {
        val newProfile = CarProfile(
            carName = carName.trim(),
            spz = spz.trim().uppercase(),
            allowedFuelTypes = if (allowedFuelTypes.isEmpty()) CarProfile.ALL_FUEL_TYPES else allowedFuelTypes,
            isSetupComplete = true
        )
        carProfileRepository.saveCarProfile(newProfile)
        _carProfile.value = newProfile
        _isOnboardingDialogOpen.value = false
        _isCarProfileDialogOpen.value = false

        // Reset fuel type filter if current filter is no longer in allowed fuel types
        if (_fuelTypeFilter.value != null && !newProfile.allowedFuelTypes.contains(_fuelTypeFilter.value)) {
            _fuelTypeFilter.value = null
        }
    }

    fun openCarProfileDialog() {
        _isCarProfileDialogOpen.value = true
    }

    fun closeCarProfileDialog() {
        _isCarProfileDialogOpen.value = false
    }

    fun dismissOnboardingDialog() {
        _isOnboardingDialogOpen.value = false
    }

    fun openExportOptionsDialog() {
        if (uiState.value.rawRecords.isEmpty()) {
            _snackbarMessage.value = AppStrings.noDataToExport(_currentLanguage.value)
            return
        }
        _isExportOptionsDialogOpen.value = true
    }

    fun closeExportOptionsDialog() {
        _isExportOptionsDialogOpen.value = false
    }

    fun exportAndShare(context: Context) {
        _isExportOptionsDialogOpen.value = false
        val records = uiState.value.rawRecords
        if (records.isEmpty()) {
            _snackbarMessage.value = AppStrings.noDataToExport(_currentLanguage.value)
            return
        }
        com.example.util.CsvExporter.exportAndShareRecords(
            context = context,
            records = records,
            lang = _currentLanguage.value
        )
    }

    fun saveExportToUri(context: Context, uri: android.net.Uri) {
        _isExportOptionsDialogOpen.value = false
        val records = uiState.value.rawRecords
        if (records.isEmpty()) {
            _snackbarMessage.value = AppStrings.noDataToExport(_currentLanguage.value)
            return
        }
        val success = com.example.util.CsvExporter.writeRecordsToUri(context, uri, records)
        if (success) {
            _snackbarMessage.value = AppStrings.exportSaveSuccess(_currentLanguage.value)
        } else {
            _snackbarMessage.value = AppStrings.exportSaveError(_currentLanguage.value)
        }
    }

    fun importDataFromUri(context: Context, uri: android.net.Uri) {
        viewModelScope.launch {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                if (inputStream == null) {
                    _snackbarMessage.value = AppStrings.importFailed(_currentLanguage.value)
                    return@launch
                }
                val records = com.example.util.CsvImporter.parseCsv(inputStream)
                if (records.isEmpty()) {
                    _snackbarMessage.value = AppStrings.importFailed(_currentLanguage.value)
                } else {
                    repository.insertAll(records)
                    _snackbarMessage.value = AppStrings.importSuccess(records.size, _currentLanguage.value)
                }
            } catch (e: Exception) {
                _snackbarMessage.value = AppStrings.importFailed(_currentLanguage.value)
            }
        }
    }

    fun openThemeDialog() {
        _isThemeDialogOpen.value = true
    }

    fun closeThemeDialog() {
        _isThemeDialogOpen.value = false
    }

    fun setThemePalette(palette: com.example.ui.theme.AppThemePalette) {
        _selectedThemePalette.value = palette
        carProfileRepository.saveThemePalette(palette)
        _isThemeDialogOpen.value = false
    }
}
