package com.pablo.tvschedule.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pablo.tvschedule.domain.model.Actor
import com.pablo.tvschedule.domain.model.getActor
import com.pablo.tvschedule.ui.common.ShowImage

@Composable
fun ActorItem(
    modifier: Modifier = Modifier,
    actor: Actor,
    onItemClick: (Int) -> Unit = { }
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ShowImage(
            image = actor.image,
            contentDescription = actor.characterName,
            modifier = Modifier
                .width(90.dp)
                .height(150.dp)
        )
        ActorSummary(actor = actor)
    }
}

@Composable
fun ActorSummary(actor: Actor) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "as",
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = actor.characterName,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Episode item example")
@Composable
private fun ActorItemPreview() {
    val actor = getActor()

    ActorItem(actor = actor)
}
