package com.pablo.tvschedule.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.R
import com.pablo.tvschedule.ui.common.LoadingContent
import com.pablo.tvschedule.ui.detail.components.ActorItem
import com.pablo.tvschedule.ui.detail.components.EpisodeDetail

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit = { }
) {
    val state = viewModel.state

    DetailScreen(
        state = state,
        navigateBack = navigateBack
    )

}

@Composable
private fun DetailScreen(
    state: DetailState,
    navigateBack: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            DetailTopBar(
                title = state.episode?.name ?: stringResource(id = R.string.app_name)
            ) {
                navigateBack()
            }
        }
    ) { paddingValues ->
        if (state.isLoading) {
            LoadingContent(
                modifier = Modifier.padding(paddingValues = paddingValues)
            )
        } else {
            Column {
                EpisodeDetail(
                    modifier = Modifier.padding(paddingValues = paddingValues),
                    episode = state.episode
                )
                Text(text = "Cast")
                LazyColumn {
                    items(state.cast.size) { index ->
                        ActorItem(actor = state.cast[index])
                    }
                }
            }
        }
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
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
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
    val state = DetailState(
        isLoading = true
    )

    DetailScreen(state = state)
}