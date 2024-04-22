package com.pablo.tvschedule.presentation.detail.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pablo.tvschedule.domain.model.getActor
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.domain.model.getShow
import com.pablo.tvschedule.presentation.detail.DetailState

class DetailStatePreviewParameterProvider : PreviewParameterProvider<DetailState> {
    override val values: Sequence<DetailState> = buildList {
        add(DetailState())
        add(
            DetailState(
                isLoading = false,
                episode = getEpisode(),
                cast = listOf(
                    getActor(),
                    getActor(),
                    getActor(),
                    getActor(),
                )
            )
        )
        add(
            DetailState(
                isLoading = false,
                episode = getEpisode()
            )
        )
        add(
            DetailState(
                isLoading = false,
                episode = getEpisode(
                    hasSummary = false,
                    hasRating = false
                )
            )
        )
        add(
            DetailState(
                isLoading = false,
                episode = getEpisode(
                    showDetails = getShow(
                        hasGenres = false,
                        isEnded = false,
                        hasSummary = false
                    )
                )
            )
        )
    }.asSequence()
}