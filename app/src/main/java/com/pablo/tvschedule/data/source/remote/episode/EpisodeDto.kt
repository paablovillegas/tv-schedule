package com.pablo.tvschedule.data.source.remote.episode

import com.pablo.tvschedule.domain.model.Episode
import org.jsoup.Jsoup

data class EpisodeDto(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val airdate: String,
    val airtime: String,
    val runtime: Int,
    val rating: Rating,
    val image: Image?,
    val summary: String?,
    val _embedded: Embedded?,
    val show: ShowDto?,
) {
    fun toEpisode() = Episode(
        id = id,
        name = name,
        season = season,
        number = number,
        airDate = airdate,
        airTime = airtime,
        runtime = runtime,
        rating = if (rating.average > 0) rating.average else null,
        image = image?.medium,
        summary = parseSummary(),
        show = show?.toShow() ?: _embedded?.show?.toShow()
    )

    private fun parseSummary(): String? {
        return this.summary?.let {
            Jsoup.parse(it).text()
        }
    }
}