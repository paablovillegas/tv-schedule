package com.pablo.tvschedule.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pablo.tvschedule.presentation.common.LoadingContent

@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            SearchTopBar(
                title = state.usFormatDate(),
                homeInteraction = homeInteraction
            )
        }
    ) { paddingValues ->
        LoadingContent(
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    title: String,
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    showSearchBar: Boolean
) {
    if (!showSearchBar) return

    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(true) }

    SearchBar(
        query = text,
        onQueryChange = {
            text = it
            Log.i("searchbar", text)
        },
        onSearch = { active = false },
        active = active,
        onActiveChange = {
            Log.i("searchbar", "$active -> $text")
            active = it
        },
        placeholder = {
            Text(text = "Search Episode")
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    //TOdO: implement
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Search"
                )
            }
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        }
    ) {

    }
}