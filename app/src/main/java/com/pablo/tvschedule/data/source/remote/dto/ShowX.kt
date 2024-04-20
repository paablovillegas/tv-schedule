package com.pablo.tvschedule.data.source.remote.dto

import com.pablo.tvschedule.domain.model.Show

data class ShowX(
    val _links: LinksX,
    val averageRuntime: Int,
    val dvdCountry: Any,
    val ended: String,
    val externals: Externals,
    val genres: List<String>,
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val network: Network,
    val officialSite: String,
    val premiered: String,
    val rating: Rating,
    val runtime: Int,
    val schedule: Schedule,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val webChannel: WebChannel,
    val weight: Int
) {
    fun toShow() = Show(
        id,
        name,
        type,
        language,
        genres.joinToString(separator = ", ") { it },
        premiered,
        ended,
        rating.average,
        image.medium,
        summary
    )
}