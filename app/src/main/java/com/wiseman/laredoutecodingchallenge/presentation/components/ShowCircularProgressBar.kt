package com.wiseman.laredoutecodingchallenge.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
 fun ShowCircularProgressBar(
    modifier: Modifier,
    show: Boolean,
) {
    if (show) {
        CircularProgressIndicator(
            modifier = modifier
        )
    }
}