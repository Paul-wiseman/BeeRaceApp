package com.wiseman.laredoutecodingchallenge.data.remote.repository

import arrow.core.Either
import com.wiseman.currencyconverter.util.coroutine.DispatchProvider
import com.wiseman.laredoutecodingchallenge.data.remote.mapper.toBee
import com.wiseman.laredoutecodingchallenge.data.remote.model.RaceDurationDto
import com.wiseman.laredoutecodingchallenge.data.remote.model.RaceStatusDto
import com.wiseman.laredoutecodingchallenge.data.remote.service.BeeRaceApiService
import com.wiseman.laredoutecodingchallenge.domain.model.BeeRaceData
import com.wiseman.laredoutecodingchallenge.domain.repository.BeeRaceRepository
import com.wiseman.laredoutecodingchallenge.util.exception.BeeRaceExceptions
import com.wiseman.laredoutecodingchallenge.util.exception.ErrorMessages.API_ERROR
import com.wiseman.laredoutecodingchallenge.util.exception.ErrorMessages.INVALID_RESPONSE
import com.wiseman.laredoutecodingchallenge.util.exception.ErrorMessages.NETWORK_ERROR
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.Response
import javax.inject.Inject


class BeeRaceRepositoryImpl @Inject constructor(
    private val beeRaceApiService: BeeRaceApiService,
    private val dispatchProvider: DispatchProvider
) : BeeRaceRepository {
    override suspend fun getRaceData(): Flow<Either<BeeRaceExceptions, BeeRaceData>> = flow {
        try {
            val getBeeRaceDuration = getBeeDuration().await()
            val getBeeRaceStatus = getBeeStatus().await()
            when {
                getBeeRaceStatus.isSuccessful -> {
                    getBeeRaceStatus.body()?.let { value ->
                        emit(
                            Either.Right(
                                BeeRaceData(
                                    duration = getBeeRaceDuration.body()?.timeInSeconds ?: 0,
                                    beeList = value.beeDtoList.mapNotNull { it?.toBee() }

                                )
                            )
                        )
                    } ?: emit(Either.Left(BeeRaceExceptions.ApiError(INVALID_RESPONSE)))
                }

                getBeeRaceStatus.code() == CAPTCHA_RESPONSE_CODE -> {
                    val recaptchaUrl = getBeeRaceStatus.extractCaptchaUrl()
                    emit(Either.Left(BeeRaceExceptions.ReCaptchaError(recaptchaUrl)))
                }

                else -> {
                    emit(Either.Left(BeeRaceExceptions.ApiError(NETWORK_ERROR)))
                }
            }
        } catch (e: Exception) {
            emit(Either.Left(BeeRaceExceptions.ApiError(API_ERROR)))
        }
    }.flowOn(dispatchProvider.io())

    private suspend fun getBeeDuration(): Deferred<Response<RaceDurationDto>> {
        return coroutineScope { async { beeRaceApiService.getRaceDuration() } }
    }

    private suspend fun getBeeStatus(): Deferred<Response<RaceStatusDto>> {
        return coroutineScope { async { beeRaceApiService.getRaceStatus() } }
    }

    private fun Response<*>.extractCaptchaUrl(): String? {
            try {
                val errorBody = errorBody()?.string()
                errorBody?.let {
                    val json = Json.parseToJsonElement(it) as JsonObject
                    return json["captchaUrl"]?.jsonPrimitive?.content
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        return null
    }

    private companion object {
        const val CAPTCHA_RESPONSE_CODE = 403
    }
}