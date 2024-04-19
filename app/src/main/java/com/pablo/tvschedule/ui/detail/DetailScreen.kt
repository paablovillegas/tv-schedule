package com.pablo.tvschedule.ui.detail

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pablo.tvschedule.R
import com.pablo.tvschedule.domain.model.getEpisode
import com.pablo.tvschedule.ui.detail.components.EpisodeResume

@Composable
fun DetailScreen(
    navigateBack: () -> Unit = { }
) {
    Scaffold(
        topBar = { DetailTopBar { navigateBack() } }
    ) {
        Column(Modifier.padding(it)) {
            EpisodeResume(getEpisode())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
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