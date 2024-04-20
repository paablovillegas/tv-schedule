package com.pablo.tvschedule.data.source.remote.dto

import com.pablo.tvschedule.domain.model.Actor

data class ActorDto(
    val character: Character,
    val person: Person
) {
    fun toActor() = Actor(
        id = character.id,
        actorName = person.name,
        characterName = character.name,
        image = character.image?.medium ?: person.image?.medium
    )
}