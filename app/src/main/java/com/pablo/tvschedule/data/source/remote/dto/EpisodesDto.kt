package com.pablo.tvschedule.data.source.remote.dto

import com.pablo.tvschedule.domain.model.Episode

data class EpisodesDto(
    val _links: Links,
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image,
    val name: String,
    val number: Int,
    val rating: Rating,
    val runtime: Int,
    val season: Int,
    val show: ShowX,
    val summary: String,
    val type: String,
    val url: String
) {
    fun toEpisode() = Episode(
        id,
        name,
        season,
        number,
        airdate,
        airtime,
        runtime,
        rating.average,
        image.medium,
        summary,
        show.toShow()
    )
}