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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.Show

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
            EpisodeImage(
                name = episode.show.name,
                image = episode.image ?: episode.show.image
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                EpisodeSummary(
                    name = episode.name,
                    airDate = episode.airDate,
                    airTime = episode.airTime
                )
                ShowSummary(
                    name = episode.show.name,
                    premiered = episode.show.premiered,
                    ended = episode.show.ended,
                    rating = episode.show.rating,
                    type = episode.show.type,
                    genres = episode.show.genres
                )
                episode.summary?.let { summary ->
                    Text(text = summary)
                }
            }
        }
    }
}

@Composable
fun EpisodeImage(
    name: String,
    image: String
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
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

@Composable
fun EpisodeSummary(
    modifier: Modifier = Modifier,
    name: String,
    airDate: String,
    airTime: String
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = "$airDate $airTime",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ShowSummary(
    name: String,
    premiered: Int,
    ended: Int?,
    rating: Double,
    type: String,
    genres: List<String>
) {
    Text(
        text = "$name ($premiered - ${ended ?: ""})",
        style = TextStyle(
            color = MaterialTheme.colorScheme.secondary
        )
    )
    Text(
        text = "$rating | $type | ${genres.joinToString(separator = ",") { it }}"
    )

}

@Preview(showBackground = true, name = "Episode item example")
@Composable
private fun EpisodeItemPreview() {
    val episode = Episode(
        id = 2804307,
        name = "Lost & Found",
        show = Show(
            name = "Doc McStuffins",
            type = "Animation",
            genres = listOf("Children"),
            premiered = 2012,
            ended = 2020,
            rating = 6.3,
            image = "https://static.tvmaze.com/uploads/images/medium_portrait/22/55379.jpg"
        ),
        summary = "While one front is rained on by flames, the other is battered by boulders. With no way out and limited options, the Scouts are forced to fight against the Titans with little hope left.",
        season = 5,
        number = 11,
        airDate = "2024-04-18",
        airTime = "00:00",
        image = null
    )

    EpisodeItem(episode = episode)
}
