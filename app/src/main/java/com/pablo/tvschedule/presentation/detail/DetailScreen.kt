package com.pablo.tvschedule.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pablo.tvschedule.R
import com.pablo.tvschedule.presentation.core.LoadingContent
import com.pablo.tvschedule.presentation.core.EpisodeCard
import com.pablo.tvschedule.presentation.core.EpisodeCardOrientation
import com.pablo.tvschedule.presentation.detail.components.ActorCard
import com.pablo.tvschedule.presentation.detail.components.ShowCard
import com.pablo.tvschedule.presentation.detail.provider.DetailStatePreviewParameterProvider

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
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                ) {
                    item {
                        EpisodeCard(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            episode = state.episode,
                            orientation = EpisodeCardOrientation.VERTICAL,
                            includeShowDetails = false,
                        )
                    }

                    state.episode?.show?.let { show ->
                        item {
                            Text(
                                text = "Show",
                                style = TextStyle(
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    top = 8.dp,
                                )
                            )
                            ShowCard(
                                show = show,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }

                    if (state.cast.isNotEmpty()) {
                        item {
                            Text(
                                text = "Cast",
                                style = TextStyle(
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    top = 8.dp,
                                )
                            )
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(state.cast.size) { index ->
                                    ActorCard(actor = state.cast[index])
                                }
                            }
                        }
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
private fun DetailScreenPreview(
    @PreviewParameter(DetailStatePreviewParameterProvider::class) state: DetailState
) {
    DetailScreen(state = state)
}