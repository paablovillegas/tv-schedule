package com.pablo.tvschedule.domain.use_case

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Actor
import com.pablo.tvschedule.domain.repositories.CastRepository
import javax.inject.Inject

class CastUseCase @Inject constructor(
    private val castRepository: CastRepository
) {
    suspend operator fun invoke(showId: Int): Result<List<Actor>> {
        return castRepository.getCast(showId)
    }
}