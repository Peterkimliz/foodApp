package com.foodapp.foodapp.data.dtos

data class AuthResponse(
    val token:String,
    val data: UserDto
)
