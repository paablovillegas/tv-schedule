package com.pablo.tvschedule.data.repositories

import android.util.Log
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.data.source.remote.ScheduleApi
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.repositories.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRepository {
    override fun getSchedule(country: String, date: String): Flow<Result<List<Episode>>> = flow {
        emit(Result.Loading())
        try {
            val response = scheduleApi.getSchedule(country, date)
                .map { it.toEpisode() }
            Log.i("fetch_complete", "${response.size}")

            emit(Result.Success(data = response))
        } catch (e: Exception) {
            emit(Result.Error(message = "Something went wrong"))
        }
    }
}