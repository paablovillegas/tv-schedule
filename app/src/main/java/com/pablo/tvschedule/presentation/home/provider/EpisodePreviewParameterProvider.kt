package com.pablo.tvschedule.presentation.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.domain.model.getShow

class EpisodePreviewParameterProvider : PreviewParameterProvider<Episode> {
    override val values = sequenceOf(
        getEpisode(
            hasSummary = false,
            hasRating = false,
            showDetails = getShow(
                isEnded = false,
                hasGenres = false,
            ),
        ),
        getEpisode(),
        getEpisode(hasRating = false),
        getEpisode(hasSummary = false),
        getEpisode(
            showDetails = getShow(
                hasGenres = false
            )
        ),
        getEpisode(
            showDetails = getShow(
                isEnded = false
            )
        ),
    )
}