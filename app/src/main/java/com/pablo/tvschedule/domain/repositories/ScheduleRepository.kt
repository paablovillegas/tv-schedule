package com.pablo.tvschedule.domain.repositories

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(country: String, date: String): Flow<Result<List<Episode>>>
}