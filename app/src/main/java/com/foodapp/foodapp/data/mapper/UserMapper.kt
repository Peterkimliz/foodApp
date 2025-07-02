package com.foodapp.foodapp.data.mapper

import com.foodapp.foodapp.data.dtos.AuthResponse
import com.foodapp.foodapp.domain.models.User

fun AuthResponse.toUser(): User {
    return User(
        token = token,
        id = data.id,
        fullName = data.fullName,
        email = data.email
    );
}