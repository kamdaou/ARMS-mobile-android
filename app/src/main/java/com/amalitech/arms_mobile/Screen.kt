package com.amalitech.arms_mobile

sealed class Screen(val route: String){
    data object Home : Screen("home")
    data object HelpSupport : Screen("help_support")
    data object Login : Screen("login")
}
