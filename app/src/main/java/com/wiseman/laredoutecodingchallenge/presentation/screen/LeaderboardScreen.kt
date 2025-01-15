package com.wiseman.laredoutecodingchallenge.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiseman.laredoutecodingchallenge.R
import com.wiseman.laredoutecodingchallenge.domain.model.Bee
import com.wiseman.laredoutecodingchallenge.presentation.components.CircleWithImage
import com.wiseman.laredoutecodingchallenge.presentation.components.PositionAndName
import com.wiseman.laredoutecodingchallenge.presentation.components.TimerCard
import com.wiseman.laredoutecodingchallenge.presentation.components.WebViewComponent
import com.wiseman.laredoutecodingchallenge.presentation.viewmodel.BeeRaceViewModel
import com.wiseman.laredoutecodingchallenge.ui.theme.LaRedouteCodingChallengeTheme
import com.wiseman.laredoutecodingchallenge.ui.theme.LocalSpacing
import com.wiseman.laredoutecodingchallenge.util.formatTime
import com.wiseman.laredoutecodingchallenge.util.state.BeeRaceUiState
import com.wiseman.laredoutecodingchallenge.util.toComposeColor
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    beeRaceViewModel: BeeRaceViewModel
) {
    val state = beeRaceViewModel.beeRaceUiState.collectAsStateWithLifecycle().value

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (state) {
            is BeeRaceUiState.Error -> Toast.makeText(
                LocalContext.current,
                state.errorMessage,
                Toast.LENGTH_SHORT
            ).show()

            is BeeRaceUiState.Loading -> {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }

            is BeeRaceUiState.Success -> {
                var remainingTime by remember { mutableIntStateOf(state.beeRaceData.duration) }
                val formattedTime = formatTime(remainingTime)
                LaunchedEffect(Unit) {
                    while (remainingTime > 0) {
                        delay(1000)
                        beeRaceViewModel.getBeeRaceData()
                        remainingTime--
                    }
                }

                LeaderboardScreen(
                    modifier,
                    raceDuration = formattedTime,
                    beeList = state.beeRaceData.beeList
                )
            }

            is BeeRaceUiState.Recaptcha -> {
                state.reCaptchaUrl?.let {
                    WebViewComponent(
                        state.reCaptchaUrl
                    )
                } ?: kotlin.run {
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
    beeList: List<Bee>,
    raceDuration: String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TimerCard(
            formattedTime = raceDuration
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalSpacing.current.spaceLarge,
                    horizontal = LocalSpacing.current.spaceLarge
                )
        ) {
            itemsIndexed(beeList) { index, item ->
                LeaderboardItem(index+1, item)
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
            .padding(vertical = spacing.spaceSmall)
    ) {
        CircleWithImage(bee.color?.toComposeColor() ?: Color.Gray)
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.weight(1f)

        ) {
            bee.name?.let {
                PositionAndName(
                    Modifier
                        .padding(horizontal = spacing.spaceMedium),
                    position, it
                )
            }
        }

        if (position <= 3) {
            Image(
                painter = painterResource(id = medalIcons[position-1]),
                contentDescription = "Medal",
                modifier = Modifier
                    .size(50.dp)
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