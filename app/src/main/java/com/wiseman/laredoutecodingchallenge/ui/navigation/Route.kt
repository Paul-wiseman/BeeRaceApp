package com.wiseman.laredoutecodingchallenge.ui.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object StartScreen : Route()

    @Serializable
    data object RaceLeaderBoardScreen : Route()

    @Serializable
    data object RaceResultScreen : Route()

    @Serializable
    data class CaptchaScreen(
        val captcherUrl: String?,
    ) : Route()

}