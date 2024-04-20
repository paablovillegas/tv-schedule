package com.pablo.tvschedule.data.source.remote

import com.pablo.tvschedule.data.source.remote.dto.EpisodesList
import com.pablo.tvschedule.data.source.remote.episode.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {

    @GET("schedule")
    suspend fun getSchedule(
        @Query("country") country: String,
        @Query("date") date: String
    ): EpisodesList


    @GET("episodes/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): EpisodeDto
}