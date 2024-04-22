package com.pablo.tvschedule.presentation.home

import com.pablo.tvschedule.domain.model.Episode
import java.time.LocalDate

data class HomeState(
    val isLoading: Boolean = true,
    val schedule: List<Episode> = emptyList(),
    val showDatePickerDialog: Boolean = false,
    val date: LocalDate = LocalDate.now(),
    val secondSchedule: Map<String, List<Episode>> = mapOf()
) {
    fun formatDate(): String {
        return buildString {
            append(date.year)
            append("-")
            append(date.monthValue.toString().padStart(2, '0'))
            append("-")
            append(date.dayOfMonth.toString().padStart(2, '0'))
        }
    }

    fun usFormatDate(): String {
        return buildString {
            append(date.month)
            append(" ")
            append(date.dayOfMonth)
            append(", ")
            append(date.year)
        }
    }
}