package com.pablo.tvschedule.data.source.remote.episode

import com.pablo.tvschedule.domain.model.Show

data class ShowDto(
    val id: Int,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val premiered: String,
    val ended: String,
    val rating: Rating,
    val image: Image,
    val summary: String,
) {
    fun toShow() = Show(
        id = id,
        name = name,
        type = type,
        language = language,
        genres = genres.joinToString(separator = ", ") { it },
        premiered = premiered.split("-")[0],
        ended = ended.split("-")[0],
        rating = rating.average,
        image = image.medium,
        summary = summary
    )
}