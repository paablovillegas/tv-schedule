package com.pablo.tvschedule.data.repositories

import android.util.Log
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.data.source.remote.CastApi
import com.pablo.tvschedule.domain.model.Actor
import com.pablo.tvschedule.domain.repositories.CastRepository
import javax.inject.Inject


class CastRepositoryImpl @Inject constructor(
    private val castApi: CastApi
) : CastRepository {
    override suspend fun getCast(showId: Int): Result<List<Actor>> {
        val result = try {
            castApi.getCast(showId).map { it.toActor() }
        } catch (e: Exception) {
            Log.i("fetch_complete", e.stackTraceToString())
            return Result.Error(message = e.stackTraceToString())
        }
        Log.i("fetch_complete", "Success ${result.size}: $result")
        return Result.Success(data = result)
    }
}