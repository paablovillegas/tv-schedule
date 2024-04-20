package com.pablo.tvschedule.domain.use_case

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) {
    suspend operator fun invoke(id: Int): Result<Episode> {
        return episodeRepository.getEpisode(id)
    }
}