package com.pablo.tvschedule.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.presentation.common.EpisodeData
import com.pablo.tvschedule.presentation.common.EpisodeSummary
import com.pablo.tvschedule.presentation.common.ImagePainter
import com.pablo.tvschedule.presentation.common.ShowData

@Composable
fun EpisodeDetail(
    modifier: Modifier = Modifier,
    episode: Episode?
) {
    episode ?: return

    Column(
        modifier = modifier
    ) {
        ImagePainter(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentDescription = episode.name,
            image = episode.image
        )
        Column(
            modifier = Modifier.padding(all = 4.dp)
        ) {
            EpisodeData(episode = episode)
            ShowData(show = episode.show)
            EpisodeSummary(summary = episode.summary, cropText = false)
        }
    }
}

@Composable
fun EpisodeDetail(episode: Episode) {

}

@Preview
@Composable
private fun EpisodeDetailPreview() {
    EpisodeDetail(episode = getEpisode())
}