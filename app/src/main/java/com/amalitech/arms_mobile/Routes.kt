package com.amalitech.arms_mobile

sealed class Routes(val route: String){
    data object Home : Routes("/") {
        data object ClockIt : Routes("/clock-it")
        data object Leaves : Routes("/leaves")
        data object Loans : Routes("/loans")
    }
    data object Leaves : Routes("/leaves/all")
    data object Celebrations : Routes("/celebrations/all")
    data object HelpSupport : Routes("/help")
    data object Login : Routes("/auth")
}

