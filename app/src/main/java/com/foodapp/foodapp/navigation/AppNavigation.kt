package com.foodapp.foodapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.foodapp.foodapp.features.auth.presentation.login.LoginScreen
import com.foodapp.foodapp.features.auth.presentation.onboard.OnBoardScreen
import com.foodapp.foodapp.features.auth.presentation.signup.SignUpScreen
import com.foodapp.foodapp.features.home.presentation.Home
import com.foodapp.foodapp.features.restaurant.RestaurantDetailScreen

@Composable
fun AppNavigation() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = OnBoard,

    ) {
        composable<OnBoard>() {
            OnBoardScreen(
                navHostController=navHostController
            )
        }

        composable<SignUp>() {
            SignUpScreen(navHostController=navHostController)
        }
        composable<Login>() {
            LoginScreen(navHostController=navHostController)
        }
        composable<Home>() {
            Home(navHostController=navHostController)
        }
        composable<RestaurantDetail>() {
            val args = it.toRoute<RestaurantDetail>()
            RestaurantDetailScreen(
                navHostController = navHostController,
                imageUrl = args.imageUrl,
                title = args.title,
                restaurantId = args.restaurantId
            )
        }

    }

}