package com.pablo.tvschedule.presentation.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.Show
import com.pablo.tvschedule.presentation.home.provider.EpisodePreviewParameterProvider

enum class EpisodeCardOrientation {
    VERTICAL,
    HORIZONTAL
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeCard(
    modifier: Modifier = Modifier,
    episode: Episode?,
    orientation: EpisodeCardOrientation = EpisodeCardOrientation.HORIZONTAL,
    includeShowDetails: Boolean = true,
    onItemClick: (Int) -> Unit = { }
) {
    episode ?: return

    Card(
        onClick = { onItemClick(episode.id) },
        modifier = modifier,
        shape = CardDefaults.outlinedShape,
    ) {
        val content: @Composable (() -> Unit) = {
            val isHorizontalCard = orientation == EpisodeCardOrientation.HORIZONTAL

            ImagePainter(
                image = episode.show?.image,
                contentDescription = episode.show?.name,
                modifier = if (isHorizontalCard) {
                    Modifier
                        .width(90.dp)
                        .height(150.dp)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 6.dp,
                        end = 6.dp,
                        bottom = if (isHorizontalCard) 0.dp else 6.dp,
                        top = if (isHorizontalCard) 6.dp else 0.dp,
                    ),
            ) {
                EpisodeCardDetail(episode = episode)
                if (includeShowDetails) {
                    ShowData(show = episode.show)
                }
                EpisodeSummary(summary = episode.summary)
            }
        }

        if (orientation == EpisodeCardOrientation.HORIZONTAL) {
            Row {
                content()
            }
        } else {
            Column {
                content()
            }
        }
    }
}

@Composable
private fun EpisodeCardDetail(
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
                ),
                modifier = Modifier.weight(1f)
            )
            RatingScore(rating = episode.rating)
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
                text = episode.airTime,
                style = TextStyle(
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}

@Composable
private fun ShowData(show: Show?) {
    show ?: return

    Column {
        Text(
            text = "${show.name} (${show.premiered} - ${show.ended ?: ""})",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary
            )
        )
        Text(
            text = listOfNotNull(show.type, show.genres)
                .filterNot { it.isBlank() }
                .joinToString(separator = " | ") { it },
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

@Composable
private fun EpisodeSummary(
    summary: String?,
) {
    summary ?: return

    Text(
        text = summary,
        style = TextStyle(
            fontStyle = FontStyle.Italic
        ),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true, name = "Episode item example")
@Composable
private fun EpisodeCardPreview(
    @PreviewParameter(EpisodePreviewParameterProvider::class) episode: Episode
) {
    Column(
        modifier = Modifier.padding(horizontal = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        EpisodeCard(
            episode = episode,
            orientation = EpisodeCardOrientation.HORIZONTAL
        )
        EpisodeCard(
            episode = episode,
            orientation = EpisodeCardOrientation.VERTICAL,
            includeShowDetails = false
        )
    }
}
