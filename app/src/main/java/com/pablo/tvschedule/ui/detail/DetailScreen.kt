package com.pablo.tvschedule.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.detail.components.EpisodeResume

@Composable
fun DetailScreen(
    navigateBack: () -> Unit = { }
) {
    val episode = getEpisode()

    Scaffold(
        topBar = { DetailTopBar(title = episode.name) { navigateBack() } }
    ) {
        EpisodeResume(
            modifier = Modifier.padding(paddingValues = it),
            episode = episode
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen()
}