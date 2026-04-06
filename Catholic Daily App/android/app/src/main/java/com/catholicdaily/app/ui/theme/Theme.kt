package com.catholicdaily.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme =
    lightColorScheme(
        primary = Color(0xFF2C3E50),
        onPrimary = Color(0xFFECF0F1),
        primaryContainer = Color(0xFFBDC3C7),
        surface = Color(0xFFF8F9FA),
        onSurface = Color(0xFF1A1A1A),
    )

@Composable
fun CatholicDailyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        content = content,
    )
}
