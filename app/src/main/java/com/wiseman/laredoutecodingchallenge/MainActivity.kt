package com.wiseman.laredoutecodingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wiseman.laredoutecodingchallenge.presentation.screen.HomeScreen
import com.wiseman.laredoutecodingchallenge.presentation.screen.StartScreen
import com.wiseman.laredoutecodingchallenge.presentation.viewmodel.BeeRaceViewModel
import com.wiseman.laredoutecodingchallenge.ui.navigation.Route
import com.wiseman.laredoutecodingchallenge.ui.theme.LaRedouteCodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val beeRaceViewModel by viewModels<BeeRaceViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaRedouteCodingChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = Route.StartScreen
                    ) {
                        composable<Route.StartScreen> {
                            StartScreen(
                                Modifier.padding(innerPadding),
                                onStart = {
                                    navHostController.navigate(
                                        Route.RaceLeaderBoardScreen
                                    )
                                })
                        }

                        composable<Route.RaceLeaderBoardScreen> { navBackStackEntry: NavBackStackEntry ->
                            HomeScreen(
                                beeRaceViewModel = beeRaceViewModel
                            )
                        }

                        composable<Route.RaceResultScreen> { navBackStackEntry: NavBackStackEntry ->
                            // could not be completed
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LaRedouteCodingChallengeTheme {


    }
}