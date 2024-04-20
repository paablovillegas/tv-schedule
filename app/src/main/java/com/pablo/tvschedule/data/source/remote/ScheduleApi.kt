package com.pablo.tvschedule.data.source.remote

import com.pablo.tvschedule.data.source.remote.dto.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {

    @GET("schedule")
    suspend fun getSchedule(
        @Query("country") country: String,
        @Query("date") date: String
    ): List<EpisodeDto>
}