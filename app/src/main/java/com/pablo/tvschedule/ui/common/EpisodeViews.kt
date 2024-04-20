package com.pablo.tvschedule.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode

@Composable
fun EpisodeData(
    modifier: Modifier = Modifier,
    episode: Episode
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "${episode.season}x${episode.number}: ${episode.name}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            episode.rating?.let { rating ->
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Rating",
                        modifier = Modifier
                            .height(18.dp)
                            .padding(end = 2.dp)
                    )
                    Text(
                        text = "$rating",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Duration: ${episode.runtime} min.",
                style = TextStyle(
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "${episode.airDate} ${episode.airTime}",
                style = TextStyle(
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}

@Composable
fun EpisodeSummary(description: String?) {
    description ?: return

    Text(
        text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            .toString(),
        style = TextStyle(
            fontStyle = FontStyle.Italic
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun EpisodeSummaryPreview() {
    EpisodeData(episode = getEpisode())
}