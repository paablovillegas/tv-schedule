package com.pablo.tvschedule.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val airDate: String,
    val airTime: String,
    val runtime: Int,
    val rating: Double?,
    val image: String?,
    val summary: String?,
    val show: Show,
)

fun getEpisode() = Episode(
    id = 1,
    name = "The Change Constant",
    season = 12,
    number = 23,
    airDate = "2019-05-16",
    airTime = "20:00",
    runtime = 30,
    rating = 7.6,
    image = "https://static.tvmaze.com/uploads/images/medium_landscape/197/493238.jpg",
    summary = "\u003Cp\u003ESheldon and Amy await big news.\u003C/p\u003E",
    show = getShow()
)