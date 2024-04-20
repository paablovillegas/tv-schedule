package com.pablo.tvschedule.ui.home.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.pablo.tvschedule.R
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.common.EpisodeData
import com.pablo.tvschedule.ui.common.EpisodeSummary
import com.pablo.tvschedule.ui.common.ShowData

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
            ShowImage(
                name = episode.show?.name,
                image = episode.show?.image
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

@Composable
fun ShowImage(
    name: String?,
    image: String?
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image ?: R.drawable.ic_launcher_background)
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(90.dp)
            .height(150.dp)
    )
}

@Preview(showBackground = true, name = "Episode item example")
@Composable
private fun EpisodeItemPreview() {
    val episode = getEpisode()

    EpisodeItem(episode = episode)
}
