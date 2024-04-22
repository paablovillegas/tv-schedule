package com.pablo.tvschedule.presentation.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pablo.tvschedule.presentation.home.HomeState
import java.time.LocalDate

class HomeStatePreviewParameterProvider : PreviewParameterProvider<HomeState> {
    override val values = sequenceOf(
        HomeState(
            isLoading = true,
            date = LocalDate.now()
        ),
        HomeState(
            isLoading = false,
            filteredSchedule = mapOf(900 to EpisodePreviewParameterProvider().values.toList()),
            date = LocalDate.now()
        )
    )
}
