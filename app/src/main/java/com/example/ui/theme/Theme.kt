package com.example.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkBlueColorScheme = darkColorScheme(
    primary = DarkBluePrimary,
    secondary = DarkBlueSecondary,
    tertiary = DarkBlueTertiary,
    background = DarkBlueBackground,
    surface = DarkBlueSurface,
    surfaceVariant = DarkBlueSurfaceVariant,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC),
    onSurfaceVariant = Color(0xFFCBD5E1),
    primaryContainer = Color(0xFF1E3A8A),
    onPrimaryContainer = Color(0xFFDBEAFE)
)

private val PastelMintColorScheme = darkColorScheme(
    primary = MintPrimary,
    secondary = MintSecondary,
    tertiary = MintTertiary,
    background = MintBackground,
    surface = MintSurface,
    surfaceVariant = MintSurfaceVariant,
    onPrimary = Color(0xFF042F2C),
    onSecondary = Color(0xFF052E16),
    onBackground = Color(0xFFF0FDF4),
    onSurface = Color(0xFFF0FDF4),
    onSurfaceVariant = Color(0xFFCCFBF1),
    primaryContainer = Color(0xFF115E59),
    onPrimaryContainer = Color(0xFFCCFBF1)
)

private val PastelLavenderColorScheme = darkColorScheme(
    primary = LavenderPrimary,
    secondary = LavenderSecondary,
    tertiary = LavenderTertiary,
    background = LavenderBackground,
    surface = LavenderSurface,
    surfaceVariant = LavenderSurfaceVariant,
    onPrimary = Color(0xFF2E1065),
    onSecondary = Color(0xFF4C0519),
    onBackground = Color(0xFFF5F3FF),
    onSurface = Color(0xFFF5F3FF),
    onSurfaceVariant = Color(0xFFDDD6FE),
    primaryContainer = Color(0xFF5B21B6),
    onPrimaryContainer = Color(0xFFEDE9FE)
)

private val PastelCoralColorScheme = darkColorScheme(
    primary = CoralPrimary,
    secondary = CoralSecondary,
    tertiary = CoralTertiary,
    background = CoralBackground,
    surface = CoralSurface,
    surfaceVariant = CoralSurfaceVariant,
    onPrimary = Color(0xFF450A0A),
    onSecondary = Color(0xFF4C0519),
    onBackground = Color(0xFFFFF1F2),
    onSurface = Color(0xFFFFF1F2),
    onSurfaceVariant = Color(0xFFFFE4E6),
    primaryContainer = Color(0xFF991B1B),
    onPrimaryContainer = Color(0xFFFEE2E2)
)

private val PastelAmberColorScheme = darkColorScheme(
    primary = AmberPalettePrimary,
    secondary = AmberPaletteSecondary,
    tertiary = AmberPaletteTertiary,
    background = AmberPaletteBackground,
    surface = AmberPaletteSurface,
    surfaceVariant = AmberPaletteSurfaceVariant,
    onPrimary = Color(0xFF422006),
    onSecondary = Color(0xFF431407),
    onBackground = Color(0xFFFEFCE8),
    onSurface = Color(0xFFFEFCE8),
    onSurfaceVariant = Color(0xFFFEF08A),
    primaryContainer = Color(0xFF854D0E),
    onPrimaryContainer = Color(0xFFFEF9C3)
)

fun getPaletteColorScheme(palette: AppThemePalette): ColorScheme {
    return when (palette) {
        AppThemePalette.DARK_BLUE -> DarkBlueColorScheme
        AppThemePalette.PASTEL_MINT -> PastelMintColorScheme
        AppThemePalette.PASTEL_LAVENDER -> PastelLavenderColorScheme
        AppThemePalette.PASTEL_CORAL -> PastelCoralColorScheme
        AppThemePalette.PASTEL_AMBER -> PastelAmberColorScheme
    }
}

@Composable
fun TankovaniTheme(
    palette: AppThemePalette = AppThemePalette.DARK_BLUE,
    content: @Composable () -> Unit,
) {
    val colorScheme = getPaletteColorScheme(palette)
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

