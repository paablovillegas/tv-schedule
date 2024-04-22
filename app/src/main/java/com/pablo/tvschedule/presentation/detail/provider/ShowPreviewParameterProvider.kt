package com.pablo.tvschedule.presentation.detail.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pablo.tvschedule.domain.model.Show
import com.pablo.tvschedule.domain.model.getShow

class ShowPreviewParameterProvider : PreviewParameterProvider<Show> {
    override val values = sequenceOf(
        getShow(),
        getShow(
            hasGenres = false
        ),
        getShow(
            isEnded = false
        ),
        getShow(
            hasRating = false
        ),
        getShow(
            hasSummary = false
        ),
        getShow(
            hasGenres = false,
            isEnded = false,
            hasRating = false,
            hasSummary = false
        )
    )
}