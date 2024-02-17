package com.amalitech.arms_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.arms_mobile.screens.HomeScreen
import com.amalitech.arms_mobile.screens.LoginInScreen
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.example.frontend_masters_tut.HelpScreen
import java.io.File

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
                    // Redundant Data File, Not Created YET
                    val persistenceFile = File("context.file", "token_data.pb")
                    val dataStore : DataStore<Preferences> = PreferenceDataStoreFactory.create {
                        persistenceFile
                    }
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                    ){
                        composable(Screen.Login.route){
                            LoginInScreen(
                                navController = navController,
                                dataStore = TokenDataStore(dataStore)
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
