package com.foodapp.foodapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodapp.foodapp.features.auth.login.LoginScreen
import com.foodapp.foodapp.features.auth.onboard.OnBoardScreen
import com.foodapp.foodapp.features.auth.signup.SignUpScreen
import com.foodapp.foodapp.features.home.Home

@Composable
fun AppNavigation() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = AppNavigationRoutes.OnBoard.route
    ) {

        composable(route = AppNavigationRoutes.OnBoard.route){
            OnBoardScreen(
                navHostController=navHostController
            )
        }

        composable(route = AppNavigationRoutes.SignUp.route){
            SignUpScreen(navHostController=navHostController)
        }
        composable(route = AppNavigationRoutes.Login.route){
            LoginScreen(navHostController=navHostController)
        }
        composable(route = AppNavigationRoutes.Home.route){
            Home(navHostController=navHostController)
        }

    }

}