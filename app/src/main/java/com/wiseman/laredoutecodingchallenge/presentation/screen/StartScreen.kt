package com.wiseman.laredoutecodingchallenge.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wiseman.laredoutecodingchallenge.R
import com.wiseman.laredoutecodingchallenge.domain.model.Bee
import com.wiseman.laredoutecodingchallenge.ui.theme.LaRedouteCodingChallengeTheme

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onStart: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = onStart
        ) {
            Text(
                text = stringResource(R.string.start_bee_race)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    LaRedouteCodingChallengeTheme {
        LeaderboardItem(
            2, Bee("BeeWare", "#d68b6b")
        )
    }
}
