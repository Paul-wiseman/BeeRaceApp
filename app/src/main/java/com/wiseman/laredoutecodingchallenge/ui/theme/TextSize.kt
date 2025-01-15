package com.wiseman.laredoutecodingchallenge.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextSizes(
    val small: TextUnit = 12.sp,
    val body: TextUnit = 16.sp,
    val medium: TextUnit = 18.sp,
    val large: TextUnit = 20.sp,
    val extraLarge: TextUnit = 24.sp,
    val titleSmall: TextUnit = 20.sp,
    val titleMedium: TextUnit = 24.sp,
    val titleLarge: TextUnit = 28.sp,
    val headline: TextUnit = 32.sp,
    val displaySmall: TextUnit = 36.sp,
    val displayMedium: TextUnit = 45.sp,
    val displayLarge: TextUnit = 57.sp
)

val LocalTextSizes = compositionLocalOf { TextSizes() }
