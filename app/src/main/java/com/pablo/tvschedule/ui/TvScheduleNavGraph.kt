package com.pablo.tvschedule.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pablo.tvschedule.ui.detail.DetailScreen
import com.pablo.tvschedule.ui.home.HomeScreen


@Composable
fun TvScheduleNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: TvScheduleActions,
    startDestination: String = Screen.Home.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen { id ->
                navigationActions.navigateToDetail(id)
            }
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            DetailScreen {
                navigationActions.navigateToHome()
            }
        }
    }
}