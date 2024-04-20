package com.pablo.tvschedule.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.domain.use_case.ScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getScheduleUseCase: ScheduleUseCase
) : ViewModel() {

    var state by mutableStateOf(
        HomeState(
            isLoading = true,
            schedule = listOf(getEpisode(), getEpisode(), getEpisode())
        )
    )
        private set

    init {
        getSchedule()
    }

    fun getSchedule() {
        viewModelScope.launch {
            getScheduleUseCase("US", "2022-01-01").also { result ->
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
}