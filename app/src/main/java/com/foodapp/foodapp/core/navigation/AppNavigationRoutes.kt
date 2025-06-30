package com.foodapp.foodapp.core.navigation

sealed class AppNavigationRoutes (val route:String){
    data object OnBoard:AppNavigationRoutes("auth")
    data object SignUp:AppNavigationRoutes("signup")
    data object Login:AppNavigationRoutes("login")
    data object Home:AppNavigationRoutes("home")


}