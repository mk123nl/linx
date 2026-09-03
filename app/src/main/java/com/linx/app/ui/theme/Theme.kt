package com.linx.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 玻璃表面：半透明的白/深色，让背景透出来
private val LightColors = lightColorScheme(
    primary = LinxColors.Brand,
    onPrimary = Color.White,
    secondary = LinxColors.Accent,
    background = LinxColors.BgLight,
    surface = Color(0xB3FFFFFF),
    onSurface = LinxColors.InkLight,
    onSurfaceVariant = LinxColors.InkSubLight,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF7C86FF),
    onPrimary = Color.White,
    secondary = LinxColors.Accent,
    background = LinxColors.BgDark,
    surface = Color(0x8C1A1B26),
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
