package com.pablo.tvschedule.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pablo.tvschedule.ui.theme.TvScheduleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvScheduleTheme {
                TvScheduleActivity()
            }
        }
    }
}

@Composable
fun TvScheduleActivity() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        TvScheduleActions(navController)
    }

    TvScheduleNavGraph(
        navController = navController,
        navigationActions = navigationActions
    )
}
