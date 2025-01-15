package com.wiseman.laredoutecodingchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.wiseman.laredoutecodingchallenge.domain.model.Bee
import com.wiseman.laredoutecodingchallenge.domain.model.BeeRaceData
import com.wiseman.laredoutecodingchallenge.domain.repository.BeeRaceRepository
import com.wiseman.laredoutecodingchallenge.util.exception.BeeRaceExceptions
import com.wiseman.laredoutecodingchallenge.util.state.BeeRaceUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeeRaceViewModel @Inject constructor(
    private val repository: BeeRaceRepository,
) : ViewModel() {

    private val _beeRaceUiState = MutableStateFlow<BeeRaceUiState>(BeeRaceUiState.Loading)
    val beeRaceUiState: StateFlow<BeeRaceUiState> = _beeRaceUiState

    init {
        getBeeRaceData()
    }

    private fun getBeeRaceData() {
        viewModelScope.launch {

            repository.getRaceData().collectLatest { data: Either<BeeRaceExceptions, BeeRaceData> ->
                when (data) {
                    is Either.Left -> {
                        when (data.value) {
                            is BeeRaceExceptions.ApiError -> {
                                _beeRaceUiState.value = BeeRaceUiState.Error(
                                    errorMessage = (data.value as BeeRaceExceptions.ApiError).message

                                )
                            }

                            is BeeRaceExceptions.ReCaptchaError -> {
                                _beeRaceUiState.value = BeeRaceUiState.Recaptcha(
                                    reCaptchaUrl = (data.value as BeeRaceExceptions.ReCaptchaError).captchaUrl
                                )
                            }
                        }
                    }

                    is Either.Right -> {
                        _beeRaceUiState.value = BeeRaceUiState.Success(
                            beeRaceData = data.value
                        )
                    }
                }
            }
        }
    }
}