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
import com.pablo.tvschedule.domain.model.Episode
import com.pablo.tvschedule.domain.model.Show
import com.pablo.tvschedule.ui.home.components.EpisodeItem

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = stringResource(id = R.string.app_name))
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onEpisodeClick: (Int) -> Unit = { }
) {
    val episodes = listOf(
        Episode(
            id = 2804307,
            name = "Lost & Found",
            show = Show(
                name = "Doc McStuffins",
                type = "Animation",
                genres = listOf("Children"),
                premiered = 2012,
                ended = 2020,
                rating = 6.3,
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/22/55379.jpg"
            ),
            summary = null,
            season = 5,
            number = 11,
            airDate = "2024-04-18",
            airTime = "00:00",
            image = "https://static.tvmaze.com/uploads/images/medium_portrait/510/1275225.jpg"
        ),
        Episode(
            id = 2804307,
            name = "Lost & Found",
            show = Show(
                name = "Doc McStuffins",
                type = "Animation",
                genres = listOf("Children"),
                premiered = 2012,
                ended = 2020,
                rating = 6.3,
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/22/55379.jpg"
            ),
            summary = null,
            season = 5,
            number = 11,
            airDate = "2024-04-18",
            airTime = "00:00",
            image = null
        ),
        Episode(
            id = 2804307,
            name = "Lost & Found",
            show = Show(
                name = "Doc McStuffins",
                type = "Animation",
                genres = listOf("Children"),
                premiered = 2012,
                ended = 2020,
                rating = 6.3,
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/22/55379.jpg"
            ),
            summary = null,
            season = 5,
            number = 11,
            airDate = "2024-04-18",
            airTime = "00:00",
            image = null
        ),
        Episode(
            id = 2804307,
            name = "Lost & Found",
            show = Show(
                name = "Doc McStuffins",
                type = "Animation",
                genres = listOf("Children"),
                premiered = 2012,
                ended = 2020,
                rating = 6.3,
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/22/55379.jpg"
            ),
            summary = null,
            season = 5,
            number = 11,
            airDate = "2024-04-18",
            airTime = "00:00",
            image = null
        ),
        Episode(
            id = 2804307,
            name = "Lost & Found",
            show = Show(
                name = "Doc McStuffins",
                type = "Animation",
                genres = listOf("Children"),
                premiered = 2012,
                ended = 2020,
                rating = 6.3,
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/22/55379.jpg"
            ),
            summary = null,
            season = 5,
            number = 11,
            airDate = "2024-04-18",
            airTime = "00:00",
            image = null
        ),
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

