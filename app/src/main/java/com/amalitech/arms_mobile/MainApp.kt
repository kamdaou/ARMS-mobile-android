package com.amalitech.arms_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.amalitech.arms_mobile.ui.views.auth.MicrosoftLoginScreen
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationScreen
import com.amalitech.arms_mobile.ui.views.help.HelpScreen
import com.amalitech.arms_mobile.ui.views.home.HomeScreen
import com.amalitech.arms_mobile.ui.views.leaves.WhoIsOutScreen


@Composable
fun MainApp() {
    val navController: NavHostController = rememberNavController()

    ARMSMobileTheme {

        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
        ) {
            composable(Routes.Login.route) {
                MicrosoftLoginScreen(
                    navController = navController,
                )
            }
            composable(Routes.HelpSupport.route) {
                HelpScreen()
            }
            composable(Routes.Home.route) {
                HomeScreen(
                    navigateToCelebration = { navController.navigate(Routes.Celebrations.route) },
                    navigateToLeaves = { navController.navigate(Routes.Leaves.route) }
                )
            }
            composable(Routes.Leaves.route) {
                WhoIsOutScreen(
                    navController = navController
                )
            }
            composable(Routes.Celebrations.route) {
                CelebrationScreen(
                    popBackStack = { navController.popBackStack() }
                )
            }
        }
    }
}