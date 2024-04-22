package com.pablo.tvschedule.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pablo.tvschedule.domain.model.Actor
import com.pablo.tvschedule.domain.model.getActor
import com.pablo.tvschedule.presentation.core.ImagePainter

@Composable
fun ActorCard(
    modifier: Modifier = Modifier,
    actor: Actor,
) {
    Card(
        modifier = modifier,
        shape = CardDefaults.outlinedShape,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .width(250.dp)
                .padding(end = 4.dp)
        ) {
            ImagePainter(
                image = actor.image,
                contentDescription = actor.characterName,
                modifier = Modifier
                    .width(90.dp)
                    .height(150.dp)
            )
            ActorSummary(actor = actor)
        }
    }
}

@Composable
fun ActorSummary(actor: Actor) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = actor.actorName,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            buildAnnotatedString {
                append("as ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    append(actor.characterName)

                }
            },
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}

@Preview(showBackground = true, name = "Episode item example")
@Composable
private fun ActorItemPreview() {
    val actor = getActor()

    ActorCard(actor = actor)
}
