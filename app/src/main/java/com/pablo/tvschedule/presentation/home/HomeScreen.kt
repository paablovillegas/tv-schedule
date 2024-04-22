package com.pablo.tvschedule.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.presentation.core.EpisodeCard
import com.pablo.tvschedule.presentation.core.LoadingContent
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
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                HomeContent(
                    schedule = state.secondSchedule,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    schedule: Map<String, List<Episode>>,
    homeInteraction: (HomeInteraction) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        for ((time, episodes) in schedule) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(4.dp)
                ) {
                    Text(
                        text = time,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    for (episode in episodes) {
                        EpisodeCard(episode = episode) { id ->
                            homeInteraction(HomeInteraction.EpisodeClick(id))
                        }
                    }
                }
            }
        }

        /*
        items(schedule.size) { index ->
            EpisodeCard(episode = schedule[index]) { id ->
                homeInteraction(HomeInteraction.EpisodeClick(id))
            }
        } */
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
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeStatePreviewParameterProvider::class) state: HomeState
) {
    HomeScreen(state = state)
}