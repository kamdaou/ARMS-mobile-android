package com.amalitech.arms_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.arms_mobile.screens.HomeScreen
import com.amalitech.arms_mobile.screens.LoginInScreen
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.example.frontend_masters_tut.HelpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ARMSMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                    ){
                        composable(Screen.Login.route){
                            LoginInScreen(
                                navController = navController,
                            )
                        }
                        composable(Screen.HelpSupport.route){
                            HelpScreen()
                        }
                        composable(Screen.Home.route){
                            HomeScreen()
                        }
                    }
                }

            }
        }
    }
}
