package com.pablo.tvschedule.presentation.core

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingScore(
    modifier: Modifier = Modifier,
    rating: Double?
) {
    rating ?: return

    Row(
        verticalAlignment = Alignment.CenterVertically,
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
            textAlign = TextAlign.End,
            modifier = modifier.testTag("episodeRating")
        )
    }
}

@Preview
@Composable
private fun RatingScorePreview() {
    RatingScore(rating = 8.0)
}