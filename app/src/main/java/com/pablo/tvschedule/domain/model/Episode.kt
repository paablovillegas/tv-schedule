package com.pablo.tvschedule.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val show: Show,
    val summary: String?,
    val season: Int,
    val number: Int,
    val airDate: String,
    val airTime: String,
    val image: String?
)