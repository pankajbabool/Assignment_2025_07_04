package com.example.assignment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = ColorDF2128PrimaryRed,
    secondary = Color464646,
    tertiary = ColorDF2128PrimaryRed.copy(alpha = .5f),
    background = ColorWhite,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MontserratTypography(),
        content = content
    )
}