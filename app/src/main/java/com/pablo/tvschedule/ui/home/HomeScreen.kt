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
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.R
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.home.components.EpisodeItem

@Composable
fun HomeScreen(
    onEpisodeClick: (Int) -> Unit = { },
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    HomeScreen(
        episodes = state.schedule
    ) { onEpisodeClick(it) }

}

@Composable
private fun HomeScreen(
    episodes: List<Episode>,
    onEpisodeClick: (Int) -> Unit = { },
) {
    Scaffold(
        topBar = { HomeTopBar() }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier.padding(paddingValues = paddingValues),
            episodes = episodes,
            onEpisodeClick = onEpisodeClick
        )
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
    episodes: List<Episode>,
    onEpisodeClick: (Int) -> Unit = { }
) {

    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(episodes.size) { index ->
                EpisodeItem(episode = episodes[index]) {
                    onEpisodeClick(it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

