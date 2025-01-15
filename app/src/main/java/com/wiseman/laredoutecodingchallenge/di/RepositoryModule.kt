package com.wiseman.laredoutecodingchallenge.di

import com.wiseman.currencyconverter.util.coroutine.DispatchProvider
import com.wiseman.laredoutecodingchallenge.data.remote.repository.BeeRaceRepositoryImpl
import com.wiseman.laredoutecodingchallenge.data.remote.service.BeeRaceApiService
import com.wiseman.laredoutecodingchallenge.domain.repository.BeeRaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBeeApiService(
        retrofit: Retrofit
    ): BeeRaceApiService = retrofit.create()

    @Singleton
    @Provides
    fun provideBeeRaceRepository(
        beeRaceApiService: BeeRaceApiService,
        dispatchProvider: DispatchProvider,
    ): BeeRaceRepository = BeeRaceRepositoryImpl(
        beeRaceApiService = beeRaceApiService,
        dispatchProvider = dispatchProvider
    )

}