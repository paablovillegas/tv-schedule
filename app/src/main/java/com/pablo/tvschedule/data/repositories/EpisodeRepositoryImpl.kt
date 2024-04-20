package com.pablo.tvschedule.data.repositories

import android.util.Log
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.data.source.remote.EpisodeApi
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi
) : EpisodeRepository {

    override suspend fun getEpisode(id: Int): Result<Episode> {
        val response = try {
            episodeApi.getEpisode(id).toEpisode()
        } catch (e: Exception) {
            Log.i("fetch_complete", "Error ${e.stackTraceToString()}")
            return Result.Error(message = e.stackTraceToString())
        }
        Log.i("fetch_complete", "Error $response")
        return Result.Success(data = response)
    }
}