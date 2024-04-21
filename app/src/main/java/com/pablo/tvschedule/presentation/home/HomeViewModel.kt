package com.pablo.tvschedule.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.use_case.ScheduleUseCase
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
            getScheduleUseCase("US", state.formatDate()).also { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            isLoading = false,
                            schedule = result.data ?: emptyList()
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
                            schedule = emptyList()
                        )
                    }
                }
            }
        }
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

    fun setDate(dateInMillis: Long) {
        val date = Instant.ofEpochMilli(dateInMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        state = state.copy(
            isLoading = true,
            date = date
        )
        getSchedule()
    }
}