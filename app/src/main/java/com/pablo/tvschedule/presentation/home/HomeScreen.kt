package com.pablo.tvschedule.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.presentation.common.LoadingContent
import com.pablo.tvschedule.presentation.home.components.EpisodeItem
import java.time.LocalDate
import java.time.ZoneOffset

@Composable
fun HomeScreen(
    onEpisodeClick: (Int) -> Unit = { },
    onSearchClick: () -> Unit = { },
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val homeInteraction: (HomeInteraction) -> Unit = { interaction ->
        when (interaction) {
            is HomeInteraction.EpisodeClick -> {
                onEpisodeClick(interaction.id)
            }

            is HomeInteraction.ShowDatePicker -> {
                viewModel.showDatePicker()
            }

            is HomeInteraction.HideDatePicker -> {
                viewModel.hideDatePicker()
            }

            is HomeInteraction.ChangeScheduleDate -> {
                viewModel.setDate(interaction.dateInMillis)
            }

            is HomeInteraction.SearchClick -> {
                onSearchClick()
            }
        }
    }

    HomeScreen(
        state = state,
        homeInteraction = homeInteraction
    )

}

@Composable
private fun HomeScreen(
    state: HomeState,
    homeInteraction: (HomeInteraction) -> Unit = { },
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                title = state.usFormatDate(),
                homeInteraction = homeInteraction
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            LoadingContent(
                modifier = Modifier.padding(paddingValues = paddingValues)
            )
        } else {
            HomeContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                schedule = state.schedule,
                homeInteraction = homeInteraction
            )
            CustomDatePicker(
                showDatePickerDialog = state.showDatePickerDialog,
                currentDate = state.date,
                homeInteraction = homeInteraction
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    schedule: List<Episode>,
    homeInteraction: (HomeInteraction) -> Unit = { }
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(schedule.size) { index ->
                EpisodeItem(episode = schedule[index]) { id ->
                    homeInteraction(HomeInteraction.EpisodeClick(id))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String,
    homeInteraction: (HomeInteraction) -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = {
                homeInteraction(HomeInteraction.ShowDatePicker())
            }) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Select date"
                )
            }
            IconButton(onClick = {
                homeInteraction(HomeInteraction.SearchClick())
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Show"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    showDatePickerDialog: Boolean,
    currentDate: LocalDate,
    homeInteraction: (HomeInteraction) -> Unit
) {
    if (!showDatePickerDialog) return

    val state = rememberDatePickerState(
        initialSelectedDateMillis = currentDate
            .atStartOfDay()
            .toInstant(ZoneOffset.MIN)
            .toEpochMilli()
    )
    DatePickerDialog(
        onDismissRequest = {
            homeInteraction(HomeInteraction.HideDatePicker())
        },
        confirmButton = {
            TextButton(onClick = {
                state.selectedDateMillis?.let { millis ->
                    homeInteraction(HomeInteraction.ChangeScheduleDate(millis))
                }
                homeInteraction(HomeInteraction.HideDatePicker())
            }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                homeInteraction(HomeInteraction.HideDatePicker())
            }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = state)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeScreenParameterProvider::class) state: HomeState
) {
    HomeScreen(state = state)
}

class HomeScreenParameterProvider : PreviewParameterProvider<HomeState> {
    override val values = sequenceOf(
        HomeState(
            isLoading = true,
            date = LocalDate.now()
        ),
        HomeState(
            isLoading = false,
            schedule = listOf(getEpisode(), getEpisode(), getEpisode()),
            date = LocalDate.now()
        )
    )
}
