package com.pablo.tvschedule.data.source.remote.episode

import com.pablo.tvschedule.domain.model.Episode

data class EpisodeDto(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val airdate: String,
    val airtime: String,
    val runtime: Int,
    val rating: Rating,
    val image: Image,
    val summary: String,
    val _embedded: Embedded,
) {
    fun toEpisode() = Episode(
        id = id,
        name = name,
        season = season,
        number = number,
        airDate = airdate,
        airTime = airtime,
        runtime = runtime,
        rating = rating.average,
        image = image.medium,
        summary = summary,
        show = _embedded.show.toShow()
    )
}