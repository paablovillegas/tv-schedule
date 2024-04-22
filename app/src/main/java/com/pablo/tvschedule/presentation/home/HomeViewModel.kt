package com.pablo.tvschedule.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.use_case.ScheduleUseCase
import com.pablo.tvschedule.presentation.core.formatDate
import com.pablo.tvschedule.presentation.core.fromHour
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getScheduleUseCase: ScheduleUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getSchedule()
    }

    private fun getSchedule() {
        viewModelScope.launch {
            getScheduleUseCase("US", state.date.formatDate()).also { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            isLoading = false,
                            schedule = result.data ?: mapOf(),
                            filteredSchedule = result.data ?: mapOf(),
                            startHour = 0,
                            endHour = 2359
                        )
                    }

                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }

                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false,
                            schedule = mapOf()
                        )
                    }
                }
            }
        }
    }

    private fun filterSchedule() {
        state = state.copy(
            filteredSchedule = state.schedule
                .filterNot { it.key.fromHour() < state.startHour }
                .filterNot { it.key.fromHour() > state.endHour }
        )
    }

    fun showDatePicker() {
        state = state.copy(
            showDatePickerDialog = true
        )
    }

    fun hideDatePicker() {
        state = state.copy(
            showDatePickerDialog = false
        )
    }

    fun showTimePicker() {
        state = state.copy(
            showTimePickerDialog = true
        )
    }

    fun hideTimePicker() {
        state = state.copy(
            showTimePickerDialog = false
        )
    }

    fun setStartHour(hour: String) {
        state = state.copy(startHour = hour.fromHour())
        filterSchedule()
    }

    fun setEndHour(hour: String) {
        state = state.copy(endHour = hour.fromHour())
        filterSchedule()
    }

    fun setDate(dateInMillis: Long) {
        val date = Instant.ofEpochMilli(dateInMillis)
            .atZone(ZoneId.of("ECT"))
            .toLocalDate()
        state = state.copy(
            isLoading = true,
            date = date
        )
        getSchedule()
    }
}