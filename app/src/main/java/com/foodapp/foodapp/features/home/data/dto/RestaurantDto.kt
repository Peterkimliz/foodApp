package com.foodapp.foodapp.features.home.data.dto

data class RestaurantDto(
    val address: String,
    val category: CategoryDto,
    val createdAt: String,
    val distance: Int,
    val id: Int,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val user: UserDto
)