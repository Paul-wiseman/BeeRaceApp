package com.wiseman.laredoutecodingchallenge.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wiseman.laredoutecodingchallenge.R
import com.wiseman.laredoutecodingchallenge.domain.model.Bee
import com.wiseman.laredoutecodingchallenge.domain.model.BeeRaceData
import com.wiseman.laredoutecodingchallenge.presentation.components.CircleWithImage
import com.wiseman.laredoutecodingchallenge.presentation.components.PositionAndName
import com.wiseman.laredoutecodingchallenge.presentation.components.TimerCard
import com.wiseman.laredoutecodingchallenge.presentation.components.WebViewComponent
import com.wiseman.laredoutecodingchallenge.ui.theme.LaRedouteCodingChallengeTheme
import com.wiseman.laredoutecodingchallenge.ui.theme.LocalSpacing
import com.wiseman.laredoutecodingchallenge.util.state.BeeRaceUiState
import com.wiseman.laredoutecodingchallenge.util.toComposeColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    beeRaceUiState: BeeRaceUiState
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (beeRaceUiState) {
            is BeeRaceUiState.Error -> Toast.makeText(
                LocalContext.current,
                beeRaceUiState.errorMessage,
                Toast.LENGTH_SHORT
            ).show()

            is BeeRaceUiState.Loading -> {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }

            is BeeRaceUiState.Success -> {
                LeaderboardScreen(
                    modifier,
                    beeRaceUiState.beeRaceData
                )
            }

            is BeeRaceUiState.Recaptcha -> {

                beeRaceUiState.reCaptchaUrl?.let {
                    WebViewComponent(
                        beeRaceUiState.reCaptchaUrl
                    )
                }?:kotlin.run {
                    Toast.makeText(
                        LocalContext.current,
                        stringResource(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

@Composable
private fun LeaderboardScreen(
    modifier: Modifier = Modifier,
    beeRaceData: BeeRaceData
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TimerCard(
            timer = beeRaceData.duration
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.spaceLarge,
                    horizontal = LocalSpacing.current.spaceMedium)
        ) {
            itemsIndexed(beeRaceData.beeList) { index, item ->
                LeaderboardItem(index, item)
            }
        }
    }
}

@Composable
fun LeaderboardItem(position: Int, bee: Bee) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        CircleWithImage(bee.color?.toComposeColor() ?: Color.Gray)
        bee.name?.let {
            PositionAndName(
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = spacing.spaceMedium),
                position, it
            )
        }

        if (position <= 2) {
            Image(
                painter = painterResource(id = medalIcons[position]),
                contentDescription = "Medal",
                modifier = Modifier
                    .size(50.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LeaderboardPreview() {
    LaRedouteCodingChallengeTheme {
        LeaderboardItem(
            2, Bee("BeeWare", "#d68b6b")
        )
    }
}

val medalIcons = listOf(
    R.drawable.medal_1,
    R.drawable.medal_2,
    R.drawable.medal_3
)