package com.pablo.tvschedule.presentation.home

sealed class HomeInteraction {
    class EpisodeClick(val id: Int) : HomeInteraction()
    class ShowDatePicker : HomeInteraction()
    class HideDatePicker : HomeInteraction()
    class ShowTimePicker : HomeInteraction()
    class HideTimePicker : HomeInteraction()
    class ChangeScheduleDate(val dateInMillis: Long) : HomeInteraction()
    class ChangeScheduleTime(val time: String, val isStartDate: Boolean) : HomeInteraction()
}
