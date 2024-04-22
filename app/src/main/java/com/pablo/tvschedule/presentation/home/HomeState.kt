package com.pablo.tvschedule.presentation.home

import com.pablo.tvschedule.domain.model.Episode
import java.time.LocalDate

data class HomeState(
    val isLoading: Boolean = true,
    val showDatePickerDialog: Boolean = false,
    val date: LocalDate = LocalDate.now(),
    val startHour: Int = 0,
    val endHour: Int = 2359,
    val schedule: Map<Int, List<Episode>> = mapOf(),
    val filteredSchedule: Map<Int, List<Episode>> = mapOf()
)