package com.pablo.tvschedule.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
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
import com.pablo.tvschedule.domain.model.Show
import com.pablo.tvschedule.presentation.core.ImagePainter
import com.pablo.tvschedule.presentation.core.RatingScore
import com.pablo.tvschedule.presentation.detail.provider.ShowPreviewParameterProvider

@Composable
fun ShowCard(
    modifier: Modifier = Modifier,
    show: Show
) {

    Card(modifier = modifier) {
        Row {
            ImagePainter(
                image = show.image,
                contentDescription = show.name,
                modifier = Modifier
                    .width(90.dp)
                    .height(150.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 6.dp,
                        end = 6.dp,
                        top = 6.dp,
                    ),
            ) {
                ShowCardDetail(show = show)
            }
        }
    }
}

@Composable
private fun ShowCardDetail(
    modifier: Modifier = Modifier,
    show: Show
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "${show.name} (${show.premiered} - ${show.ended ?: ""})",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.weight(1f)
            )
            RatingScore(rating = show.rating)
        }
        Text(
            text = listOfNotNull(show.type, show.genres)
                .filterNot { it.isBlank() }
                .joinToString(separator = " | ") { it },
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary
            )
        )
        Text(
            text = "Avg duration: ${show.runtime} min.",
            style = TextStyle(
                fontWeight = FontWeight.Light
            )
        )
        show.summary?.let { summary ->
            Text(
                text = summary,
                style = TextStyle(
                    fontStyle = FontStyle.Italic
                ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun ShowCardPreview(
    @PreviewParameter(ShowPreviewParameterProvider::class) show: Show
) {
    ShowCard(show = show)
}