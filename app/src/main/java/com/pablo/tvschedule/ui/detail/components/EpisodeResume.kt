package com.pablo.tvschedule.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.common.EpisodeData
import com.pablo.tvschedule.ui.common.EpisodeSummary
import com.pablo.tvschedule.ui.common.ShowData

@Composable
fun EpisodeResume(
    modifier: Modifier = Modifier,
    episode: Episode?
) {
    episode ?: return

    Column(
        modifier = modifier
    ) {
        EpisodeImage(episode = episode)
        EpisodeData(episode = episode)
        ShowData(show = episode.show)
        EpisodeSummary(description = episode.summary)
    }
}

@Composable
fun EpisodeImage(episode: Episode) {
    episode.image ?: return

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(episode.image)
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = episode.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun EpisodeDetail(episode: Episode) {

}

@Preview
@Composable
private fun Preview1() {
    EpisodeResume(episode = getEpisode())
}