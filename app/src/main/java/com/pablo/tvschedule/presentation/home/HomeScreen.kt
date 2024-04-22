package com.pablo.tvschedule.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.R
import com.pablo.tvschedule.presentation.core.CustomDropDownMenu
import com.pablo.tvschedule.presentation.core.EpisodeCard
import com.pablo.tvschedule.presentation.core.LoadingContent
import com.pablo.tvschedule.presentation.core.fromHour
import com.pablo.tvschedule.presentation.core.toHour
import com.pablo.tvschedule.presentation.core.usFormatDate
import com.pablo.tvschedule.presentation.home.components.CustomDatePickerDialog
import com.pablo.tvschedule.presentation.home.components.HomeTopBar
import com.pablo.tvschedule.presentation.home.provider.HomeStatePreviewParameterProvider

@Composable
fun HomeScreen(
    onEpisodeClick: (Int) -> Unit = { },
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val homeInteraction = getHomeInteraction(
        onEpisodeClick = onEpisodeClick,
        viewModel = viewModel
    )

    HomeScreen(
        state = state,
        homeInteraction = homeInteraction
    )

}

@Composable
fun HomeScreen(
    state: HomeState,
    homeInteraction: (HomeInteraction) -> Unit = { },
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                title = state.date.usFormatDate(),
                homeInteraction = homeInteraction
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            LoadingContent(
                modifier = Modifier.padding(paddingValues = paddingValues)
            )
        } else {
            Box(
                modifier = Modifier.padding(paddingValues)
                    .testTag("homeContent")
            ) {
                HomeContent(
                    state = state,
                    homeInteraction = homeInteraction
                )
                CustomDatePickerDialog(
                    showDatePickerDialog = state.showDatePickerDialog,
                    currentDate = state.date,
                    homeInteraction = homeInteraction
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    homeInteraction: (HomeInteraction) -> Unit = { }
) {
    Column(
        modifier = modifier.padding(
            top = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        HourSelector(
            state = state,
            homeInteraction = homeInteraction
        )
        ScheduleList(
            state = state,
            homeInteraction = homeInteraction
        )
    }
}

@Composable
private fun HourSelector(
    modifier: Modifier = Modifier,
    state: HomeState,
    homeInteraction: (HomeInteraction) -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomDropDownMenu(
            modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.fromHour),
            items = stringArrayResource(id = R.array.hours)
                .filterNot { it.fromHour() > state.endHour },
            isStartDate = true,
            selectedTime = state.startHour.toHour(),
            homeInteraction = homeInteraction
        )

        CustomDropDownMenu(
            modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.toHour),
            items = stringArrayResource(id = R.array.hours)
                .filterNot { it.fromHour() < state.startHour },
            isStartDate = false,
            selectedTime = state.endHour.toHour(),
            homeInteraction = homeInteraction
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ScheduleList(
    modifier: Modifier = Modifier,
    state: HomeState,
    homeInteraction: (HomeInteraction) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        for ((time, episodes) in state.filteredSchedule) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(4.dp)
                ) {
                    Text(
                        text = time.toHour(),
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.testTag("scheduleHourHeader")
                    )
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    for (episode in episodes) {
                        EpisodeCard(
                            modifier = Modifier.testTag("scheduleEpisodeItem"),
                            episode = episode,
                            includeAirTime = false,
                            summaryMaxLines = 3
                        ) { id ->
                            homeInteraction(HomeInteraction.EpisodeClick(id))
                        }
                    }
                }
            }
        }
    }
}

private fun getHomeInteraction(
    onEpisodeClick: (Int) -> Unit,
    viewModel: HomeViewModel
): (HomeInteraction) -> Unit = { interaction ->
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

        is HomeInteraction.ChangeScheduleTime -> {
            if (interaction.isStartDate)
                viewModel.setStartHour(interaction.time)
            else
                viewModel.setEndHour(interaction.time)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeStatePreviewParameterProvider::class) state: HomeState
) {
    HomeScreen(state = state)
}