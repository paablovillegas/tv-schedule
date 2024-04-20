package com.pablo.tvschedule.data.repositories

import android.util.Log
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.data.source.remote.ScheduleApi
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.repositories.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRepository {
    override suspend fun getSchedule(country: String, date: String): Result<List<Episode>> {
        val response = try {
            scheduleApi.getSchedule()
                .map { it.toEpisode() }
        } catch (e: Exception) {
            Log.i("fetch_complete", "Error ${e.stackTraceToString()}")
            return Result.Error(message = e.stackTraceToString())
        }
        Log.i("fetch_complete", "Error $response")
        return Result.Success(data = response)
    }
}