package com.foodapp.foodapp.features.auth.data.mapper

import com.foodapp.foodapp.features.auth.data.dtos.AuthResponse
import com.foodapp.foodapp.features.auth.domain.models.User

fun AuthResponse.toUser():User{
    return User(
        token = token,
        id = data.id,
        fullName = data.fullName,
        email = data.email
    );
}