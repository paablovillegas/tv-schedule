package com.pablo.tvschedule.presentation.detail

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pablo.tvschedule.domain.model.getActor
import com.pablo.tvschedule.domain.model.getEpisode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {
    @JvmField
    @Rule
    val rule = createComposeRule()

    @Test
    fun testLoadingContent() {
        rule.setContent {
            DetailScreen(state = DetailState())
        }

        rule.onNodeWithTag("loadingContent").assertExists()
        rule.onNodeWithTag("detailContent").assertDoesNotExist()
    }

    @Test
    fun testOnlyEpisode() {
        rule.setContent {
            DetailScreen(
                state = DetailState(
                    isLoading = false,
                    episode = getEpisode(
                        showDetails = null
                    )
                )
            )
        }

        rule.onNodeWithTag("loadingContent").assertDoesNotExist()
        rule.onNodeWithTag("detailContent").assertExists()
        rule.onNodeWithTag("episodeEpisodeContent").assertExists()
        rule.onNodeWithTag("episodeShowHeader").assertDoesNotExist()
        rule.onNodeWithTag("episodeShowContent").assertDoesNotExist()
        rule.onNodeWithTag("episodeCastHeader").assertDoesNotExist()
        rule.onNodeWithTag("episodeCastItem").assertDoesNotExist()
    }

    @Test
    fun testEpisodeShow() {
        rule.setContent {
            DetailScreen(
                state = DetailState(
                    isLoading = false,
                    episode = getEpisode()
                )
            )
        }

        rule.onNodeWithTag("loadingContent").assertDoesNotExist()
        rule.onNodeWithTag("detailContent").assertExists()
        rule.onNodeWithTag("episodeEpisodeContent").assertExists()
        rule.onNodeWithTag("episodeShowHeader").assertExists()
        rule.onNodeWithTag("episodeShowContent").assertExists()
        rule.onNodeWithTag("episodeCastHeader").assertDoesNotExist()
        rule.onNodeWithTag("episodeCastItem").assertDoesNotExist()
    }

    @Test
    fun testEpisodeShowCast() {
        rule.setContent {
            DetailScreen(
                state = DetailState(
                    isLoading = false,
                    episode = getEpisode(),
                    cast = listOf(getActor(), getActor())
                )
            )
        }

        rule.onNodeWithTag("loadingContent").assertDoesNotExist()
        rule.onNodeWithTag("detailContent").assertExists()
        rule.onNodeWithTag("episodeEpisodeContent").assertExists()
        rule.onNodeWithTag("episodeShowHeader").assertExists()
        rule.onNodeWithTag("episodeShowContent").assertExists()
        rule.onNodeWithTag("episodeCastHeader").assertExists()
        rule.onAllNodesWithTag("episodeCastItem").assertCountEquals(2)
    }
}