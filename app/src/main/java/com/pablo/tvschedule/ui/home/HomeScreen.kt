package com.pablo.tvschedule.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.R
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.common.LoadingContent
import com.pablo.tvschedule.ui.home.components.EpisodeItem

@Composable
fun HomeScreen(
    onEpisodeClick: (Int) -> Unit = { },
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    HomeScreen(
        state = state
    ) { onEpisodeClick(it) }

}

@Composable
private fun HomeScreen(
    state: HomeState,
    onEpisodeClick: (Int) -> Unit = { },
) {
    Scaffold(
        topBar = { HomeTopBar() }
    ) { paddingValues ->
        if (state.isLoading) {
            LoadingContent(
                modifier = Modifier.padding(paddingValues = paddingValues)
            )
        } else {
            HomeContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                schedule = state.schedule,
                onEpisodeClick = onEpisodeClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    schedule: List<Episode>,
    onEpisodeClick: (Int) -> Unit = { }
) {

    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(schedule.size) { index ->
                EpisodeItem(episode = schedule[index]) {
                    onEpisodeClick(it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeScreenParameterProvider::class) state: HomeState
) {
    HomeScreen(state = state)
}

class HomeScreenParameterProvider : PreviewParameterProvider<HomeState> {
    override val values = sequenceOf(
        HomeState(
            isLoading = true
        ),
        HomeState(
            isLoading = false,
            schedule = listOf(getEpisode(), getEpisode(), getEpisode())
        )
    )
}
