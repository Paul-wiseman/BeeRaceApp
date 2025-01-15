package com.wiseman.laredoutecodingchallenge.data.remote.endpoint

import com.wiseman.laredoutecodingchallenge.BuildConfig

object Endpoint {
    private const val BASE_URL = BuildConfig.BASE_URL
    const val RACE_DURATION = "$BASE_URL/bees/race/status"
   const val RACE_STATUS = "$BASE_URL/bees/race/status"
}