package com.pablo.tvschedule.presentation.detail

import com.pablo.tvschedule.domain.model.Actor
import com.pablo.tvschedule.domain.model.Episode

data class DetailState(
    val isLoading: Boolean = true,
    val episode: Episode? = null,
    val cast: List<Actor> = emptyList()
)
