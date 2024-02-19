package com.amalitech.arms_mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.arms_mobile.data.datasources.TokenDataStore
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.amalitech.arms_mobile.ui.views.auth.AuthViewModel
import com.amalitech.arms_mobile.ui.views.auth.LoginInScreen
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationScreen
import com.amalitech.arms_mobile.ui.views.help.HelpScreen
import com.amalitech.arms_mobile.ui.views.home.HomeScreen
import com.amalitech.arms_mobile.ui.views.leaves.WhoIsOutScreen


@Composable
fun MainApp() {
    val navController: NavHostController = rememberNavController()
    val authView: AuthViewModel = hiltViewModel()

    ARMSMobileTheme {

        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
        ) {
            composable(Routes.Login.route){
                LoginInScreen(
                    navController = navController,
                )
            }
            composable(Routes.HelpSupport.route){
                HelpScreen()
            }
            composable(Routes.Home.route) {
                HomeScreen(
                    navController = navController
                )
            }
            composable(Routes.Leaves.route) {
                WhoIsOutScreen(
                    navController = navController
                )
            }
            composable(Routes.Celebrations.route) {
                CelebrationScreen(
                    navController = navController
                )
            }
        }
    }
}