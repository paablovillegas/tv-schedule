package com.pablo.tvschedule.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.pablo.tvschedule.domain.model.Show
import com.pablo.tvschedule.domain.model.getShow

@Composable
fun ShowData(show: Show) {
    Column {
        Text(
            text = "${show.name} (${show.premiered} - ${show.ended ?: ""})",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary
            )
        )
        Text(
            text = "${show.type} | ${show.genres}",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ShowSummaryPreview() {
    ShowData(show = getShow())
}