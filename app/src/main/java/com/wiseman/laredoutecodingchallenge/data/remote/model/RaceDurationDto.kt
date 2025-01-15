package com.wiseman.laredoutecodingchallenge.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceDurationDto(
    @SerialName("timeInSeconds")
    val timeInSeconds: Int?
)