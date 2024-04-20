package com.pablo.tvschedule.domain.use_case

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.repositories.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(country: String, date: String): Result<List<Episode>> {
        return repository.getSchedule(country, date)
    }
}