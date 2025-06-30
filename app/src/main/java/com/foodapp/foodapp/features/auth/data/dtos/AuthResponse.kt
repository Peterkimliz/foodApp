package com.foodapp.foodapp.features.auth.data.dtos

data class AuthResponse(
    val token:String,
    val data:UserDto
)
