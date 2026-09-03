package com.linx.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = LinxColors.Brand,
    onPrimary = Color.White,
    secondary = LinxColors.Accent,
    background = LinxColors.BgLight,
    surface = Color.White,
    onSurface = LinxColors.InkLight,
    onSurfaceVariant = LinxColors.InkSubLight,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF7C86FF),
    onPrimary = Color.White,
    secondary = LinxColors.Accent,
    background = LinxColors.BgDark,
    surface = LinxColors.SurfaceDark,
    onSurface = LinxColors.InkDark,
    onSurfaceVariant = LinxColors.InkSubDark,
)

@Composable
fun LinxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
