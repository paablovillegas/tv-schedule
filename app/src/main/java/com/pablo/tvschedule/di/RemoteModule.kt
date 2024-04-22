package com.pablo.tvschedule.di

import com.pablo.tvschedule.data.source.remote.CastApi
import com.pablo.tvschedule.data.source.remote.EpisodeApi
import com.pablo.tvschedule.data.source.remote.ScheduleApi
import com.pablo.tvschedule.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideScheduleApi(
        retrofit: Retrofit
    ): ScheduleApi {
        return retrofit.create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodeApi(
        retrofit: Retrofit
    ): EpisodeApi {
        return retrofit.create(EpisodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCastApi(
        retrofit: Retrofit
    ): CastApi {
        return retrofit.create(CastApi::class.java)
    }
}