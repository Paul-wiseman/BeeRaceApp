package com.wiseman.laredoutecodingchallenge.domain.repository

import arrow.core.Either
import com.wiseman.laredoutecodingchallenge.domain.model.BeeRaceData
import com.wiseman.laredoutecodingchallenge.util.exception.BeeRaceExceptions
import kotlinx.coroutines.flow.Flow

interface BeeRaceRepository {
    suspend fun getRaceData(): Flow<Either<BeeRaceExceptions, BeeRaceData>>
}