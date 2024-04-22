package com.pablo.tvschedule.presentation.home

sealed class HomeInteraction {
    class EpisodeClick(val id: Int) : HomeInteraction()
    class ShowDatePicker : HomeInteraction()
    class HideDatePicker : HomeInteraction()
    class ChangeScheduleDate(val dateInMillis: Long) : HomeInteraction()
}
