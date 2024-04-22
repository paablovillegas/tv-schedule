package com.pablo.tvschedule.presentation.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pablo.tvschedule.domain.model.getEpisode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @JvmField
    @Rule
    val rule = createComposeRule()

    @Test
    fun testLoadingContent() {
        rule.setContent {
            HomeScreen(state = HomeState())
        }

        rule.onNodeWithTag("loadingContent").assertExists()
        rule.onNodeWithTag("homeContent").assertDoesNotExist()
    }

    @Test
    fun testEmptySchedule() {
        rule.setContent {
            HomeScreen(
                state = HomeState(
                    isLoading = false
                )
            )
        }

        rule.onNodeWithTag("loadingContent").assertDoesNotExist()
        rule.onNodeWithTag("homeContent").assertExists()
        rule.onAllNodesWithTag("scheduleHourHeader").assertCountEquals(0)
        rule.onAllNodesWithTag("scheduleEpisodeItem").assertCountEquals(0)
    }

    @Test
    fun testSingleSchedule() {
        rule.setContent {
            HomeScreen(
                state = HomeState(
                    isLoading = false,
                    filteredSchedule = mapOf(
                        900 to listOf(getEpisode())
                    )
                )
            )
        }

        rule.onNodeWithTag("loadingContent").assertDoesNotExist()
        rule.onNodeWithTag("homeContent").assertExists()
        rule.onNodeWithTag("scheduleHourHeader")
            .assertExists()
            .assertTextContains("09:00")
        rule.onAllNodesWithTag("scheduleEpisodeItem").assertCountEquals(1)
    }

    @Test
    fun testMultipleSchedule() {
        rule.setContent {
            HomeScreen(
                state = HomeState(
                    isLoading = false,
                    filteredSchedule = mapOf(
                        900 to listOf(getEpisode()),
                        1100 to listOf(getEpisode())
                    )
                )
            )
        }

        rule.onNodeWithTag("loadingContent").assertDoesNotExist()
        rule.onNodeWithTag("homeContent").assertExists()
        rule.onAllNodesWithTag("scheduleHourHeader").assertCountEquals(2)
        rule.onAllNodesWithTag("scheduleEpisodeItem").assertCountEquals(2)
    }
}