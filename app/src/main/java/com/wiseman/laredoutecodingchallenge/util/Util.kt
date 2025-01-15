package com.wiseman.laredoutecodingchallenge.util

import androidx.compose.ui.graphics.Color

fun String.toComposeColor(): Color {
    val colorString = this.removePrefix("#")
    return Color(color = colorString.toLong(16) or 0xFF000000)
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}