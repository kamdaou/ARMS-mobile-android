package com.amalitech.arms_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme


@Composable
fun MainApp() {
    val navigator: NavHostController = rememberNavController()

    ARMSMobileTheme {
        AppNavigation(navController = navigator)
    }
}