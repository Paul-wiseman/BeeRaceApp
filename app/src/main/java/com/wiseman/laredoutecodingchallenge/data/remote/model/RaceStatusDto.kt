package com.wiseman.laredoutecodingchallenge.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceStatusDto(
    @SerialName("beeList")
    val beeDtoList: List<BeeDto?>
)