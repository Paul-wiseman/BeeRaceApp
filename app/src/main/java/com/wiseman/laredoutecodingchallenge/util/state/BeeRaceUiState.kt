package com.wiseman.laredoutecodingchallenge.util.state

import com.wiseman.laredoutecodingchallenge.domain.model.BeeRaceData

sealed class BeeRaceUiState {
    data class Success(val beeRaceData: BeeRaceData) : BeeRaceUiState()
    data object Loading : BeeRaceUiState()
    data class Error(val errorMessage: String) : BeeRaceUiState()
    data class Recaptcha(val reCaptchaUrl: String):BeeRaceUiState()
}