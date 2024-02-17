package com.amalitech.arms_mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationRoute
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationScreen
import com.amalitech.arms_mobile.ui.views.home.HomeScreen
import com.amalitech.arms_mobile.ui.views.home.HomeScreenRoute
import com.amalitech.arms_mobile.ui.views.who_is_out.WhoIsOutRoute
import com.amalitech.arms_mobile.ui.views.who_is_out.WhoIsOutScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute.path,
        modifier = modifier,
    ) {
        composable(route = HomeScreenRoute.path) {
            HomeScreen(
                navController = navController
            )
        }
        composable(route = WhoIsOutRoute.path) {
            WhoIsOutScreen(
                navController = navController
            )
        }
        composable(route = CelebrationRoute.path) {
            CelebrationScreen(
                navController = navController
            )
        }
    }
}