package com.amalitech.arms_mobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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