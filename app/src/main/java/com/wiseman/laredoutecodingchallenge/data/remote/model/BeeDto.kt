package com.wiseman.laredoutecodingchallenge.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeeDto(
    @SerialName("color")
    val color: String?,
    @SerialName("name")
    val name: String?
)