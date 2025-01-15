package com.wiseman.laredoutecodingchallenge.data.remote.mapper

import com.wiseman.laredoutecodingchallenge.data.remote.model.BeeDto
import com.wiseman.laredoutecodingchallenge.domain.model.Bee


fun BeeDto.toBee() = Bee(
    name = name, color = color

)


