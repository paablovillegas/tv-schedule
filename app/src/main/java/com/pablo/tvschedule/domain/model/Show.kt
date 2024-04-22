package com.pablo.tvschedule.domain.model

data class Show(
    val id: Int,
    val name: String,
    val type: String,
    val language: String?,
    val genres: String,
    val premiered: String,
    val ended: String?,
    val runtime: Int,
    val rating: Double?,
    val image: String?,
    val summary: String?,
)

fun getShow(
    hasGenres: Boolean = true,
    isEnded: Boolean = true,
    hasImage: Boolean = true,
    hasRating: Boolean = true,
    hasSummary: Boolean = true
) = Show(
    id = 66,
    name = "The Big Bang Theory",
    type = "Scripted",
    language = "English",
    genres = if (hasGenres) "Comedy, Romance" else "",
    premiered = "2007",
    ended = if (isEnded) "2019" else null,
    runtime = 30,
    rating = if (hasRating) 8.0 else null,
    image = if (hasImage) "https://static.tvmaze.com/uploads/images/medium_portrait/173/433868.jpg" else null,
    summary = if (hasSummary) "\u003Cp\u003E\u003Cb\u003EThe Big Bang Theory\u003C/b\u003E is a comedy about brilliant physicists, Leonard and Sheldon, who are the kind of \"beautiful minds\" that understand how the universe works. But none of that genius helps them interact with people, especially women. All this begins to change when a free-spirited beauty named Penny moves in next door. Sheldon, Leonard's roommate, is quite content spending his nights playing Klingon Boggle with their socially dysfunctional friends, fellow Cal Tech scientists Wolowitz and Koothrappali. However, Leonard sees in Penny a whole new universe of possibilities... including love.\u003C/p\u003E" else null,
)
