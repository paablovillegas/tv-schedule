package com.pablo.tvschedule.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.presentation.common.EpisodeData
import com.pablo.tvschedule.presentation.common.EpisodeSummary
import com.pablo.tvschedule.presentation.common.ShowData
import com.pablo.tvschedule.presentation.common.ImagePainter

@Composable
fun EpisodeItem(
    modifier: Modifier = Modifier,
    episode: Episode,
    onItemClick: (Int) -> Unit = { }
) {
    Box(
        modifier = modifier
            .clickable { onItemClick(episode.id) }
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ImagePainter(
                image = episode.show?.image,
                contentDescription = episode.show?.name,
                modifier = Modifier
                    .width(90.dp)
                    .height(150.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                EpisodeData(episode = episode)
                ShowData(show = episode.show)
                EpisodeSummary(summary = episode.summary)
            }
        }
    }
}

@Preview(showBackground = true, name = "Episode item example")
@Composable
private fun EpisodeItemPreview() {
    val episode = getEpisode()

    EpisodeItem(episode = episode)
}
