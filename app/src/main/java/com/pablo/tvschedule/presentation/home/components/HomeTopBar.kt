package com.pablo.tvschedule.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pablo.tvschedule.presentation.home.HomeInteraction


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String,
    homeInteraction: (HomeInteraction) -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = {
                homeInteraction(HomeInteraction.ShowDatePicker())
            }) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Select date"
                )
            }
        }
    )
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    HomeTopBar(title = "Title") { }
}