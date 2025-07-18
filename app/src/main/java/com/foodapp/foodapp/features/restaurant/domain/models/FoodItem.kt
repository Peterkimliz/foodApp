package com.foodapp.foodapp.features.restaurant.domain.models

data class FoodItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val createdAt: String,
    val restaurantId: Int
)
