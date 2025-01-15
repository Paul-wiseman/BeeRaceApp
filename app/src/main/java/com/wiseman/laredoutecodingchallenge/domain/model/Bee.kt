package com.wiseman.laredoutecodingchallenge.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Bee(
    val name: String?,
    val color: String?
)
