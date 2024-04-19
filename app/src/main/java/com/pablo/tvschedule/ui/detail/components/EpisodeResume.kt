package com.pablo.tvschedule.ui.detail.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.Show

@Composable
fun EpisodeResume(episode: Episode) {
    ShowSummary(show = episode.show)
}


@Composable
fun ShowSummary(show: Show) {
    Text(
        text = "${show.name} (${show.premiered} - ${show.ended ?: ""})",
        style = TextStyle(
            color = MaterialTheme.colorScheme.secondary
        )
    )
    Text(
        text = "${show.rating} | ${show.type} | ${show.genres.joinToString(separator = ",") { it }}"
    )

}
