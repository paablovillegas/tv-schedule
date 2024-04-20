package com.pablo.tvschedule.domain.model

data class Actor(
    val id: Int,
    val actorName: String,
    val characterName: String,
    val image: String?,
)

fun getActor() = Actor(
    id = 1,
    actorName = "Jim Parsons",
    characterName = "Sheldon Lee Cooper",
    image = "https://static.tvmaze.com/uploads/images/medium_portrait/175/439561.jpg"
)