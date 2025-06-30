package com.foodapp.foodapp.features.auth.data.dtos

data class SignUpRequest(
    val fullName: String,
    val email: String,
    val password: String
)
