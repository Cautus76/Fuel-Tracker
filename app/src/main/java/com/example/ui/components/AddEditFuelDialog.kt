package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.model.FuelRecord
import com.example.util.DateUtils
import java.util.Calendar

import com.example.ui.model.AppLanguage
import com.example.util.AppStrings

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddEditFuelDialog(
    editingRecord: FuelRecord?,
    lastKnownOdometer: Double,
    onDismiss: () -> Unit,
    onSave: (dateMillis: Long, odometer: Double, litres: Double, totalPrice: Double, fuelType: String, stationName: String) -> Unit,
    lang: AppLanguage = AppLanguage.CZ,
    modifier: Modifier = Modifier
) {
    var dateMillis by remember {
        mutableStateOf(editingRecord?.dateMillis ?: System.currentTimeMillis())
    }

    var odometerText by remember {
        mutableStateOf(
            editingRecord?.odometer?.let { DateUtils.formatNumber(it, 0, lang).replace(" ", "").replace(",", ".") }
                ?: if (lastKnownOdometer > 0) (lastKnownOdometer + 450).toInt().toString() else ""
        )
    }

    var litresText by remember {
        mutableStateOf(
            editingRecord?.litres?.let { DateUtils.formatNumber(it, 2, lang).replace(" ", "").replace(",", ".") } ?: ""
        )
    }

    var priceText by remember {
        mutableStateOf(
            editingRecord?.totalPrice?.let { DateUtils.formatNumber(it, 2, lang).replace(" ", "").replace(",", ".") } ?: ""
        )
    }

    var selectedFuelType by remember {
        mutableStateOf(editingRecord?.fuelType ?: "Benzín")
    }

    var stationName by remember {
        mutableStateOf(editingRecord?.stationName ?: "")
    }

    var showDatePicker by remember { mutableStateOf(false) }

    // Error states
    var odometerError by remember { mutableStateOf<String?>(null) }
    var litresError by remember { mutableStateOf<String?>(null) }
    var priceError by remember { mutableStateOf<String?>(null) }

    // Calculated unit price preview
    val unitPricePreview by remember {
        derivedStateOf {
            val l = litresText.replace(",", ".").toDoubleOrNull() ?: 0.0
            val p = priceText.replace(",", ".").toDoubleOrNull() ?: 0.0
            if (l > 0 && p > 0) {
                p / l
            } else null
        }
    }

    val fuelTypes = listOf("Benzín", "Nafta", "LPG", "CNG", "Elektro")
    val popularStations = listOf("ORLEN", "Shell", "MOL", "OMV", "EuroOil")

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = dateMillis
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { dateMillis = it }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(AppStrings.cancel(lang))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier.testTag("add_edit_fuel_dialog"),
        shape = RoundedCornerShape(28.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalGasStation,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(
                    text = if (editingRecord == null) AppStrings.addRecord(lang) else AppStrings.editRecord(lang),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Date Picker Field
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true }
                        .testTag("date_picker_button")
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column {
                                Text(
                                    text = AppStrings.date(lang),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = DateUtils.formatDate(dateMillis, lang = lang),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = AppStrings.editRecord(lang),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Odometer input
                OutlinedTextField(
                    value = odometerText,
                    onValueChange = {
                        odometerText = it
                        odometerError = null
                    },
                    label = { Text(AppStrings.odometer(lang)) },
                    placeholder = { Text("120500") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    suffix = { Text("km") },
                    isError = odometerError != null,
                    supportingText = odometerError?.let { { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("odometer_input"),
                    shape = RoundedCornerShape(12.dp)
                )

                // Litres input
                OutlinedTextField(
                    value = litresText,
                    onValueChange = {
                        litresText = it
                        litresError = null
                    },
                    label = { Text(AppStrings.litres(lang)) },
                    placeholder = { Text("42.5") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.LocalGasStation,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    suffix = { Text("l") },
                    isError = litresError != null,
                    supportingText = litresError?.let { { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("litres_input"),
                    shape = RoundedCornerShape(12.dp)
                )

                // Total Price input
                OutlinedTextField(
                    value = priceText,
                    onValueChange = {
                        priceText = it
                        priceError = null
                    },
                    label = { Text("${AppStrings.totalPrice(lang)} (${lang.currencySymbol})") },
                    placeholder = { Text("1615") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Payments,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    suffix = { Text(lang.currencySymbol) },
                    isError = priceError != null,
                    supportingText = priceError?.let { { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("price_input"),
                    shape = RoundedCornerShape(12.dp)
                )

                // Unit Price Feedback
                if (unitPricePreview != null) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${AppStrings.avgPricePerLitre(lang)}: ${DateUtils.formatNumber(unitPricePreview!!, lang = lang)} ${lang.currencySymbol}/l",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }

                // Fuel Type Selector Chips
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = AppStrings.fuelType(lang),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        fuelTypes.forEach { type ->
                            FilterChip(
                                selected = selectedFuelType == type,
                                onClick = { selectedFuelType = type },
                                label = { Text(type) },
                                leadingIcon = if (selectedFuelType == type) {
                                    { Icon(imageVector = Icons.Default.Check, contentDescription = null) }
                                } else null
                            )
                        }
                    }
                }

                // Station Name / Note input
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    OutlinedTextField(
                        value = stationName,
                        onValueChange = { stationName = it },
                        label = { Text(AppStrings.stationName(lang)) },
                        placeholder = { Text("ORLEN, Shell...") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Store,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("station_input"),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // Quick station suggestions
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        popularStations.forEach { station ->
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.clickable { stationName = station }
                            ) {
                                Text(
                                    text = station,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val odo = odometerText.replace(",", ".").toDoubleOrNull()
                    val lit = litresText.replace(",", ".").toDoubleOrNull()
                    val pri = priceText.replace(",", ".").toDoubleOrNull()

                    var isValid = true
                    if (odo == null || odo <= 0) {
                        odometerError = AppStrings.odometer(lang)
                        isValid = false
                    }
                    if (lit == null || lit <= 0) {
                        litresError = AppStrings.litres(lang)
                        isValid = false
                    }
                    if (pri == null || pri <= 0) {
                        priceError = AppStrings.totalPrice(lang)
                        isValid = false
                    }

                    if (isValid && odo != null && lit != null && pri != null) {
                        onSave(dateMillis, odo, lit, pri, selectedFuelType, stationName.trim())
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.testTag("save_button")
            ) {
                Text(AppStrings.save(lang), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("cancel_button")
            ) {
                Text(AppStrings.cancel(lang))
            }
        }
    )
}
