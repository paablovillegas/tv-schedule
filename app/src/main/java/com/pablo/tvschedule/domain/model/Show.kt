package com.pablo.tvschedule.domain.model

data class Show(
    val name: String,
    val type: String,
    val genres: List<String>,
    val premiered: Int,
    val ended: Int?,
    val rating: Double,
    val image: String,
)