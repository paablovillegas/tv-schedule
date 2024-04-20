package com.pablo.tvschedule.data.repositories

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
            return Result.Error(message = "Something went wrong")
        }
        return Result.Success(data = response)
    }
}