package com.pablo.tvschedule.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.presentation.core.EpisodeCard
import com.pablo.tvschedule.presentation.core.EpisodeCardOrientation

@Composable
fun EpisodeDetail(
    modifier: Modifier = Modifier,
    episode: Episode?
) {
    episode ?: return

    Column(
        modifier = modifier
    ) {
        EpisodeCard(
            episode = episode,
            orientation = EpisodeCardOrientation.VERTICAL,
            includeShowDetails = false
        )
        //ShowData(show = episode.show)
    }
}

@Preview
@Composable
private fun EpisodeDetailPreview() {
    EpisodeDetail(episode = getEpisode())
}