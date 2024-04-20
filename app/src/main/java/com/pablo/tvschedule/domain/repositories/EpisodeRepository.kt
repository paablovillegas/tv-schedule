package com.pablo.tvschedule.domain.repositories

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisode(id: Int): Result<Episode>
}