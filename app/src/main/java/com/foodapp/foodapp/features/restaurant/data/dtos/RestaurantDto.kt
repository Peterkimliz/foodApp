package com.foodapp.foodapp.features.restaurant.data.dtos

data class RestaurantDto(
    val address: String,
    val createdAt: String,
    val distance: Int,
    val id: Int,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,

)