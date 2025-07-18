package com.foodapp.foodapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.foodapp.foodapp.features.auth.presentation.login.LoginScreen
import com.foodapp.foodapp.features.auth.presentation.onboard.OnBoardScreen
import com.foodapp.foodapp.features.auth.presentation.signup.SignUpScreen
import com.foodapp.foodapp.features.home.presentation.Home
import com.foodapp.foodapp.features.restaurant.presentation.restaurantdetails.RestaurantDetailScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation() {
    val navHostController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navHostController,
            startDestination = OnBoard,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                ) + fadeIn(animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(200)
                ) + fadeOut(animationSpec = tween(200))
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)  // try 300ms instead of 10ms
                ) + fadeIn(animationSpec = tween(400))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(200)
                ) + fadeOut(animationSpec = tween(200))
            }


        ) {
            composable<OnBoard>() {
                OnBoardScreen(
                    navHostController = navHostController
                )
            }

            composable<SignUp>() {
                SignUpScreen(navHostController = navHostController)
            }
            composable<Login>() {
                LoginScreen(navHostController = navHostController)
            }
            composable<Home>() {
                Home(
                    navHostController = navHostController,
                    animatedContentScope = this@SharedTransitionLayout
                )
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


}