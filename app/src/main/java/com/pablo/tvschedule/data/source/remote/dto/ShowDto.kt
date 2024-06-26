package com.pablo.tvschedule.data.source.remote.dto

import com.pablo.tvschedule.domain.model.Show
import org.jsoup.Jsoup

data class ShowDto(
    val id: Int,
    val name: String,
    val type: String,
    val language: String?,
    val genres: List<String>,
    val premiered: String,
    val ended: String?,
    val runtime: Int,
    val rating: Rating,
    val image: Image?,
    val summary: String?,
) {
    fun toShow() = Show(
        id = id,
        name = name,
        type = type,
        language = language,
        genres = genres.joinToString(separator = ", ") { it },
        premiered = premiered.split("-")[0],
        ended = ended?.split("-")?.get(0),
        runtime = runtime,
        rating = if (rating.average > 0) rating.average else null,
        image = image?.medium,
        summary = parseSummary()
    )

    private fun parseSummary(): String? {
        return this.summary?.let {
            Jsoup.parse(it).text()
        }
    }
}