package com.wiseman.laredoutecodingchallenge.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.wiseman.laredoutecodingchallenge.R
import com.wiseman.laredoutecodingchallenge.ui.theme.LocalSpacing
import com.wiseman.laredoutecodingchallenge.ui.theme.LocalTextSizes

@Composable
fun TimerCard(
    modifier: Modifier = Modifier,
    formattedTime: String,
    backgroundColor: Color = Color.Black,
) {
    val spacing = LocalSpacing.current
    val textSize = LocalTextSizes.current
    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium,
                    vertical = spacing.spaceLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.time_remaining),
                color = Color.White,
                modifier = Modifier
                    .padding(spacing.spaceMedium)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = formattedTime,
                color = Color.White,
                fontSize = textSize.headline,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = spacing.spaceSmall)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTimerCard() {
    TimerCard(
        formattedTime = "0.04"
    )
}