package com.github.gustavobarbosab.imc.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme = lightColorScheme(
    primary = Purple10,
    onPrimary = Color.White,
    primaryContainer = Purple60,
    onPrimaryContainer = Color.White,
    secondary = Grey20,
    onSecondary = Color.White,
    secondaryContainer = Grey30,
    onSecondaryContainer = Color.White,
    tertiary = Red30,
    onTertiary = Red90,
    tertiaryContainer = Red20,
    onTertiaryContainer = Grey95,
    error = Red30,
    onError = Red90,
    errorContainer = Red20,
    onErrorContainer = Red80,
    background = Grey10,
    onBackground = Color.White,
    surface = Grey10,
    onSurface = Color.White,
    surfaceVariant = Grey30,
    onSurfaceVariant = Color.White,
)

@Composable
fun BMITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}