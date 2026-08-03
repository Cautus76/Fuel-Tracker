package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.model.AppLanguage
import com.example.ui.theme.AppThemePalette
import com.example.ui.theme.DarkBluePrimary
import com.example.ui.theme.DarkBlueSecondary
import com.example.ui.theme.LavenderPrimary
import com.example.ui.theme.LavenderSecondary
import com.example.ui.theme.MintPrimary
import com.example.ui.theme.MintSecondary
import com.example.ui.theme.CoralPrimary
import com.example.ui.theme.CoralSecondary
import com.example.ui.theme.AmberPalettePrimary
import com.example.ui.theme.AmberPaletteSecondary
import com.example.util.AppStrings

@Composable
fun ThemePickerDialog(
    selectedPalette: AppThemePalette,
    lang: AppLanguage,
    onPaletteSelect: (AppThemePalette) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = AppStrings.themeTitle(lang),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AppThemePalette.values().forEach { palette ->
                    val isSelected = palette == selectedPalette
                    val (title, color1, color2) = when (palette) {
                        AppThemePalette.DARK_BLUE -> Triple(
                            AppStrings.themeDarkBlue(lang),
                            DarkBluePrimary,
                            DarkBlueSecondary
                        )
                        AppThemePalette.PASTEL_MINT -> Triple(
                            AppStrings.themePastelMint(lang),
                            MintPrimary,
                            MintSecondary
                        )
                        AppThemePalette.PASTEL_LAVENDER -> Triple(
                            AppStrings.themePastelLavender(lang),
                            LavenderPrimary,
                            LavenderSecondary
                        )
                        AppThemePalette.PASTEL_CORAL -> Triple(
                            AppStrings.themePastelCoral(lang),
                            CoralPrimary,
                            CoralSecondary
                        )
                        AppThemePalette.PASTEL_AMBER -> Triple(
                            AppStrings.themePastelAmber(lang),
                            AmberPalettePrimary,
                            AmberPaletteSecondary
                        )
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPaletteSelect(palette) }
                            .testTag("theme_option_${palette.name.lowercase()}"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected)
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                            else
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Color swatches circle preview
                            Row(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(32.dp)
                                        .background(color1)
                                )
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(32.dp)
                                        .background(color2)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("theme_cancel_button")
            ) {
                Text(AppStrings.cancel(lang))
            }
        }
    )
}
