package com.foodapp.foodapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object OnBoard

@Serializable
object SignUp

@Serializable
object Login

@Serializable
object Home

@Serializable
data class RestaurantDetail(
    val imageUrl: String,
    val restaurantId: Int,
    val title: String
)


