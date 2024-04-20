package com.pablo.tvschedule.domain.repositories

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Actor

interface CastRepository {
    suspend fun getCast(showId: Int): Result<List<Actor>>
}