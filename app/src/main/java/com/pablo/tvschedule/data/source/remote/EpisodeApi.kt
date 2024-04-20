package com.pablo.tvschedule.data.source.remote

import com.pablo.tvschedule.data.source.remote.dto.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {

    @GET("episodes/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int,
        @Query("embed") embed: String = "show"
    ): EpisodeDto
}