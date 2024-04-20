package com.pablo.tvschedule.domain.repositories

import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    suspend fun getSchedule(country: String, date: String): Result<List<Episode>>
}