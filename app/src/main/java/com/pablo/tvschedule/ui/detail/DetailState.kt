package com.pablo.tvschedule.ui.detail

import com.pablo.tvschedule.domain.model.Episode

data class DetailState(
    val isLoading: Boolean = true,
    val episode: Episode? = null,
)
