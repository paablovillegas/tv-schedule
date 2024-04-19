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
import com.pablo.tvschedule.R
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.home.components.EpisodeItem

@Composable
fun HomeScreen(
    onEpisodeClick: (Int) -> Unit = { }
) {
    Scaffold(
        topBar = { HomeTopBar() }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            onEpisodeClick(it)
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
    onEpisodeClick: (Int) -> Unit = { }
) {
    val episodes = listOf(
        getEpisode(),
        getEpisode(),
        getEpisode(),
        getEpisode(),
        getEpisode(),
        getEpisode(),
        getEpisode(),
    )

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

