package com.foodapp.foodapp.features.home.domain.models

data class Restaurant(
    val address: String,
    val category: Category,
    val createdAt: String,
    val distance: Int,
    val id: Int,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val user: User
)