package com.wiseman.laredoutecodingchallenge.data.remote.service

import com.wiseman.laredoutecodingchallenge.data.remote.endpoint.Endpoint
import com.wiseman.laredoutecodingchallenge.data.remote.model.BeeDto
import com.wiseman.laredoutecodingchallenge.data.remote.model.RaceDurationDto
import com.wiseman.laredoutecodingchallenge.data.remote.model.RaceStatusDto
import retrofit2.Response
import retrofit2.http.GET

interface BeeRaceApiService {
    @GET(Endpoint.RACE_DURATION)
    suspend fun getRaceDuration(): Response<RaceDurationDto>

    @GET(Endpoint.RACE_STATUS)
    suspend fun getRaceStatus(): Response<List<BeeDto?>>
}