package com.pablo.tvschedule.ui.home

import com.pablo.tvschedule.data.source.remote.episode.EpisodeDto
import com.pablo.tvschedule.domain.model.Episode

data class HomeState(
    val isLoading: Boolean = false,
    val schedule: List<Episode> = emptyList(),
    val episodeDto: EpisodeDto? = null
)