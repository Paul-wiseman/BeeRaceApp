package com.wiseman.laredoutecodingchallenge.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val space12: Dp = 12.dp,
    val space100: Dp = 100.dp,
    val space200: Dp = 200.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }