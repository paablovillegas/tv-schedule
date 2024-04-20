package com.pablo.tvschedule.data.source.remote.dto

data class WebChannel(
    val country: Country,
    val id: Int,
    val name: String,
    val officialSite: String
)