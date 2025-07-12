package com.ashwin.habit_tracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = HabitGreen,
    primaryVariant = HabitGreenDark,
    secondary = HabitBlue,
    secondaryVariant = HabitBlueDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = HabitRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    onError = Color.White
)

private val LightColorPalette = lightColors(
    primary = HabitGreen,
    primaryVariant = HabitGreenDark,
    secondary = HabitBlue,
    secondaryVariant = HabitBlueDark,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = HabitRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onError = Color.White
)

@Composable
fun HabitTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

