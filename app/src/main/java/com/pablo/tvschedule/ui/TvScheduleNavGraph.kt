package com.pablo.tvschedule.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pablo.tvschedule.presentation.detail.DetailScreen
import com.pablo.tvschedule.presentation.home.HomeScreen
import com.pablo.tvschedule.presentation.search.SearchScreen


@Composable
fun TvScheduleNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route,
    navigationActions: TvScheduleActions,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onEpisodeClick = { id ->
                    navigationActions.navigateToDetail(id)
                },
                onSearchClick = {
                    navigationActions.navigateToSearch()
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            DetailScreen {
                navController.popBackStack()
            }
        }
        composable(
            route = Screen.Search.route,
        ) {
            SearchScreen()
        }
    }
}