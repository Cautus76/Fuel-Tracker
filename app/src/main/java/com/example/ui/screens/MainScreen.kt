package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.example.data.model.FuelRecord
import com.example.ui.components.AddEditFuelDialog
import com.example.ui.components.EmptyStateCard
import com.example.ui.components.FuelConsumptionChart
import com.example.ui.components.FuelRecordCard
import com.example.ui.components.StatsOverviewCard
import com.example.ui.viewmodel.FuelUiState
import com.example.ui.viewmodel.FuelViewModel
import com.example.util.ClearAllProtectionValidator

import androidx.compose.material.icons.filled.Language
import com.example.ui.model.AppLanguage
import com.example.util.AppStrings

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    viewModel: FuelViewModel,
    uiState: FuelUiState,
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val snackbarHostState = remember { SnackbarHostState() }
    var showMenu by remember { mutableStateOf(false) }
    var showLangMenu by remember { mutableStateOf(false) }
    var isSearchActive by remember { mutableStateOf(false) }
    var recordToDelete by remember { mutableStateOf<FuelRecord?>(null) }
    var showClearAllConfirm by remember { mutableStateOf(false) }

    // Handle snackbar messages
    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSnackbarMessage()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.LocalGasStation,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = AppStrings.appName(lang),
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = AppStrings.consumptionChartTitle(lang),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    // Search toggle button
                    IconButton(
                        onClick = {
                            isSearchActive = !isSearchActive
                            if (!isSearchActive) viewModel.setSearchQuery("")
                        },
                        modifier = Modifier.testTag("search_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (isSearchActive) Icons.Default.Clear else Icons.Default.Search,
                            contentDescription = AppStrings.searchPlaceholder(lang)
                        )
                    }

                    // Language Selector Dropdown
                    Box {
                        TextButton(
                            onClick = { showLangMenu = true },
                            modifier = Modifier.testTag("language_selector_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = lang.code,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }

                        DropdownMenu(
                            expanded = showLangMenu,
                            onDismissRequest = { showLangMenu = false }
                        ) {
                            AppLanguage.values().forEach { appLang ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "${appLang.displayName} (${appLang.code})",
                                            fontWeight = if (appLang == lang) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    onClick = {
                                        showLangMenu = false
                                        viewModel.setLanguage(appLang)
                                    },
                                    modifier = Modifier.testTag("lang_option_${appLang.code}")
                                )
                            }
                        }
                    }

                    // Main options menu
                    Box {
                        IconButton(
                            onClick = { showMenu = true },
                            modifier = Modifier.testTag("main_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu"
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(AppStrings.loadSampleData(lang)) },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                onClick = {
                                    showMenu = false
                                    viewModel.loadSampleData()
                                },
                                modifier = Modifier.testTag("menu_load_sample")
                            )

                            if (uiState.rawRecords.isNotEmpty()) {
                                DropdownMenuItem(
                                    text = { Text(AppStrings.clearAllData(lang)) },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    },
                                    onClick = {
                                        showMenu = false
                                        viewModel.openClearAllStep1()
                                    },
                                    modifier = Modifier.testTag("menu_clear_all")
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { viewModel.openAddDialog() },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = AppStrings.addRecord(lang)
                    )
                },
                text = { Text(AppStrings.addRecord(lang), fontWeight = FontWeight.Bold) },
                shape = RoundedCornerShape(18.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .testTag("add_fuel_fab")
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Animated Search Field
            AnimatedVisibility(
                visible = isSearchActive,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = { Text(AppStrings.searchPlaceholder(lang)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .testTag("search_input"),
                    shape = RoundedCornerShape(16.dp)
                )
            }

            // Filter Chips for Fuel Type
            if (uiState.rawRecords.isNotEmpty()) {
                val fuelTypes = listOf(
                    null to AppStrings.allFuelTypes(lang),
                    "Benzín" to "Benzín",
                    "Nafta" to "Nafta",
                    "LPG" to "LPG"
                )
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    fuelTypes.forEach { (typeVal, label) ->
                        FilterChip(
                            selected = uiState.selectedFuelTypeFilter == typeVal,
                            onClick = { viewModel.setFuelFilter(typeVal) },
                            label = { Text(label) },
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                }
            }

            // LazyColumn Content List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Item 1: Overview Card
                item {
                    StatsOverviewCard(
                        stats = uiState.stats,
                        lang = lang
                    )
                }

                // Item 2: Chart (if enough records)
                if (uiState.items.count { it.segmentConsumption != null } >= 2) {
                    item {
                        FuelConsumptionChart(
                            items = uiState.items,
                            lang = lang
                        )
                    }
                }

                // Item 3: List Section Header
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AppStrings.recordsHistory(lang),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "${uiState.items.size} ${AppStrings.totalRecords(lang)}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // Empty State or List Items
                if (uiState.items.isEmpty()) {
                    item {
                        EmptyStateCard(
                            onAddRecordClick = { viewModel.openAddDialog() },
                            onLoadSampleDataClick = { viewModel.loadSampleData() },
                            lang = lang
                        )
                    }
                } else {
                    items(
                        items = uiState.items,
                        key = { it.record.id }
                    ) { uiItem ->
                        FuelRecordCard(
                            uiItem = uiItem,
                            onEdit = { viewModel.openEditDialog(uiItem.record) },
                            onDelete = { recordToDelete = uiItem.record },
                            lang = lang
                        )
                    }
                }
            }
        }
    }

    // Add / Edit Dialog
    if (uiState.isAddDialogOpen) {
        AddEditFuelDialog(
            editingRecord = uiState.editingRecord,
            previousOdometer = uiState.previousOdometer,
            onDismiss = { viewModel.closeDialog() },
            onSave = { date, odo, lit, pri, fuelType, station ->
                viewModel.saveRecord(date, odo, lit, pri, fuelType, station)
            },
            lang = lang
        )
    }

    // Delete single record confirmation dialog
    recordToDelete?.let { record ->
        val dateFormatted = com.example.util.DateUtils.formatDate(record.date, lang = lang)
        val odoFormatted = com.example.util.DateUtils.formatNumber(record.odometer, 0, lang)
        AlertDialog(
            onDismissRequest = { recordToDelete = null },
            title = { Text(AppStrings.deleteConfirmTitle(lang)) },
            text = {
                Text(AppStrings.deleteRecordConfirmMsg(dateFormatted, odoFormatted, lang))
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteRecord(record)
                        recordToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.testTag("delete_record_confirm_button")
                ) {
                    Text(AppStrings.delete(lang))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { recordToDelete = null },
                    modifier = Modifier.testTag("delete_record_cancel_button")
                ) {
                    Text(AppStrings.cancel(lang))
                }
            },
            modifier = Modifier.testTag("delete_record_dialog")
        )
    }

    // Clear all records step 1 confirmation dialog
    if (uiState.isClearAllStep1Open) {
        AlertDialog(
            onDismissRequest = { viewModel.cancelClearAll() },
            title = { Text(AppStrings.clearAllStep1Title(lang)) },
            text = { Text(AppStrings.clearAllStep1Msg(lang)) },
            confirmButton = {
                Button(
                    onClick = { viewModel.proceedToClearAllStep2() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.testTag("clear_all_step1_continue")
                ) {
                    Text(AppStrings.continueButton(lang))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.cancelClearAll() },
                    modifier = Modifier.testTag("clear_all_step1_cancel")
                ) {
                    Text(AppStrings.cancel(lang))
                }
            },
            modifier = Modifier.testTag("clear_all_step1_dialog")
        )
    }

    // Clear all records step 2 permanent deletion dialog
    if (uiState.isClearAllStep2Open) {
        val isConfirmValid = ClearAllProtectionValidator.isConfirmationValid(uiState.clearAllConfirmationText)
        AlertDialog(
            onDismissRequest = { viewModel.cancelClearAll() },
            title = { Text(AppStrings.clearAllStep2Title(lang)) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(AppStrings.clearAllStep2Msg(lang))
                    OutlinedTextField(
                        value = uiState.clearAllConfirmationText,
                        onValueChange = { viewModel.updateClearAllConfirmationText(it) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("clear_all_confirmation_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.confirmClearAllData() },
                    enabled = isConfirmValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier.testTag("clear_all_step2_confirm")
                ) {
                    Text(AppStrings.permanentlyDeleteButton(lang))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.cancelClearAll() },
                    modifier = Modifier.testTag("clear_all_step2_cancel")
                ) {
                    Text(AppStrings.cancel(lang))
                }
            },
            modifier = Modifier.testTag("clear_all_step2_dialog")
        )
    }
}
