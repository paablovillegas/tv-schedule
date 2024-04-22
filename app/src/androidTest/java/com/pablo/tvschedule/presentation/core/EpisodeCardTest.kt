package com.pablo.tvschedule.presentation.core

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pablo.tvschedule.domain.model.getEpisode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EpisodeCardTest {
    @JvmField
    @Rule
    val rule = createComposeRule()

    @Test
    fun testEpisodeCard() {
        rule.setContent {
            EpisodeCard(
                episode = getEpisode()
            )
        }

        rule.onNodeWithTag("episodeItemName", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemRating", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemDuration", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemAirTime", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemShowTitle", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemShowTypeGenres", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemSummary", useUnmergedTree = true).assertExists()
    }

    @Test
    fun testEpisodeCardNoRating() {
        rule.setContent {
            EpisodeCard(
                episode = getEpisode(
                    hasRating = false
                ),
            )
        }

        rule.onNodeWithTag("episodeItemName", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemRating", useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag("episodeItemDuration", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemAirTime", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemShowTitle", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemShowTypeGenres", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemSummary", useUnmergedTree = true).assertExists()
    }

    @Test
    fun testEpisodeCardNoShow() {
        rule.setContent {
            EpisodeCard(
                episode = getEpisode(),
                includeShowDetails = false
            )
        }

        rule.onNodeWithTag("episodeItemName", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemRating", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemDuration", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemAirTime", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemShowTitle", useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag("episodeItemShowTypeGenres", useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag("episodeItemSummary", useUnmergedTree = true).assertExists()
    }

    @Test
    fun testEpisodeCardNoAirTime() {
        rule.setContent {
            EpisodeCard(
                episode = getEpisode(),
                includeAirTime = false
            )
        }

        rule.onNodeWithTag("episodeItemName", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemRating", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemDuration", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemAirTime", useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag("episodeItemShowTitle", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemShowTypeGenres", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("episodeItemSummary", useUnmergedTree = true).assertExists()
    }
}