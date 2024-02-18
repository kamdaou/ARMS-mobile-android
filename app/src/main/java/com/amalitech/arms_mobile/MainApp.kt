package com.amalitech.arms_mobile

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.amalitech.arms_mobile.ui.views.auth.LoginInScreen
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationScreen
import com.amalitech.arms_mobile.ui.views.help.HelpScreen
import com.amalitech.arms_mobile.ui.views.home.HomeScreen
import com.amalitech.arms_mobile.ui.views.leaves.WhoIsOutScreen
import java.io.File
import javax.inject.Inject


@Composable
fun MainApp() {
    val navController: NavHostController = rememberNavController()

//    val persistenceFile = File("context.file", "token_data.preferences_pb")
//    val persistenceFile = File("preferences", "datastore.preferences_pb")
//    val dataStore : DataStore<Preferences> = PreferenceDataStoreFactory.create {
//        persistenceFile
//    }

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