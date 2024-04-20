package com.pablo.tvschedule.ui.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablo.tvschedule.data.Result
import com.pablo.tvschedule.domain.use_case.EpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val episodeUseCase: EpisodeUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init {
        getEpisode()
    }

    private fun getEpisode() {
        savedStateHandle.get<Int>("id")?.let { episodeId ->
            viewModelScope.launch {
                episodeUseCase(id = episodeId).also { result ->
                    when (result) {
                        is Result.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }

                        is Result.Success -> {
                            state = state.copy(
                                isLoading = false,
                                episode = result.data
                            )
                        }

                        is Result.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                        }
                    }
                }

            }

        }
    }

}