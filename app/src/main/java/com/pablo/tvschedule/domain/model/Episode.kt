package com.pablo.tvschedule.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val airDate: String,
    val airTime: String,
    val runTime: Int,
    val rating: Double,
    val image: String?,
    val summary: String?,
    val show: Show,
)

fun getEpisode() = Episode(
    id = 1,
    name = "Lost & Found",
    season = 5,
    number = 11,
    airDate = "2024-04-18",
    airTime = "00:00",
    runTime = 60,
    rating = 7.5,
    image = null,
    summary = "While one front is rained on by flames, the other is battered by boulders. With no way out and limited options, the Scouts are forced to fight against the Titans with little hope left.",
    show = getShow()
)