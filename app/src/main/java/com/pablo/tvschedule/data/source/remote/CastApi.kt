package com.pablo.tvschedule.data.source.remote

import com.pablo.tvschedule.data.source.remote.dto.ActorDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CastApi {
    @GET("shows/{id}/cast")
    suspend fun getCast(
        @Path("id") showId: Int
    ): List<ActorDto>
}