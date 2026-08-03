package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CarProfile
import com.example.ui.model.AppLanguage
import com.example.util.AppStrings

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CarProfileDialog(
    currentProfile: CarProfile,
    isFirstLaunch: Boolean,
    onSave: (carName: String, spz: String, selectedFuelTypes: List<String>) -> Unit,
    onDismiss: () -> Unit,
    onLoadSampleData: (() -> Unit)? = null,
    lang: AppLanguage = AppLanguage.CZ,
    modifier: Modifier = Modifier
) {
    var carName by remember { mutableStateOf(currentProfile.carName) }
    var spz by remember { mutableStateOf(currentProfile.spz) }
    var selectedFuelTypes by remember {
        mutableStateOf(currentProfile.allowedFuelTypes.toSet())
    }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = {
            if (!isFirstLaunch) {
                onDismiss()
            }
        },
        modifier = modifier.testTag("car_profile_dialog"),
        shape = RoundedCornerShape(28.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                    )
                }
                Text(
                    text = AppStrings.vehicleSetupTitle(lang),
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = AppStrings.vehicleSetupSubtitle(lang),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Car Name Field
                OutlinedTextField(
                    value = carName,
                    onValueChange = {
                        carName = it
                        errorMessage = null
                    },
                    label = { Text(AppStrings.carNameLabel(lang)) },
                    placeholder = { Text(AppStrings.carNamePlaceholder(lang)) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.DirectionsCar, contentDescription = null)
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("car_name_input")
                )

                // SPZ Field
                OutlinedTextField(
                    value = spz,
                    onValueChange = {
                        spz = it
                        errorMessage = null
                    },
                    label = { Text(AppStrings.spzLabel(lang)) },
                    placeholder = { Text(AppStrings.spzPlaceholder(lang)) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Badge, contentDescription = null)
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("spz_input")
                )

                // Fuel Types Selection Header
                Text(
                    text = AppStrings.selectFuelsHeader(lang),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Fuel Type Chips
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CarProfile.ALL_FUEL_TYPES.forEach { fuel ->
                        val isSelected = selectedFuelTypes.contains(fuel)
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                selectedFuelTypes = if (isSelected) {
                                    selectedFuelTypes - fuel
                                } else {
                                    selectedFuelTypes + fuel
                                }
                                errorMessage = null
                            },
                            label = { Text(fuel) },
                            leadingIcon = if (isSelected) {
                                {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            } else null,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("fuel_chip_${fuel.lowercase()}")
                        )
                    }
                }

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.testTag("setup_error_text")
                    )
                }

                onLoadSampleData?.let { onSample ->
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )

                    OutlinedButton(
                        onClick = {
                            val finalName = if (carName.isBlank()) "Škoda Octavia" else carName.trim()
                            val finalSpz = if (spz.isBlank()) "1ABC234" else spz.trim().uppercase()
                            val finalFuels = if (selectedFuelTypes.isEmpty()) CarProfile.ALL_FUEL_TYPES else selectedFuelTypes.toList()
                            onSave(finalName, finalSpz, finalFuels)
                            onSample()
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("car_profile_load_sample_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = AppStrings.loadSampleData(lang),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (carName.isBlank() || spz.isBlank() || selectedFuelTypes.isEmpty()) {
                        errorMessage = AppStrings.setupValidationError(lang)
                    } else {
                        onSave(carName, spz, selectedFuelTypes.toList())
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.testTag("save_car_profile_button")
            ) {
                Text(
                    if (isFirstLaunch) AppStrings.saveAndContinue(lang)
                    else AppStrings.save(lang)
                )
            }
        },
        dismissButton = if (!isFirstLaunch) {
            {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("cancel_car_profile_button")
                ) {
                    Text(AppStrings.cancel(lang))
                }
            }
        } else null
    )
}
