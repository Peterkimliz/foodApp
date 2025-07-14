package com.foodapp.foodapp.features.home.data.mapper

import com.foodapp.foodapp.features.home.data.dto.CategoryDto
import com.foodapp.foodapp.features.home.data.dto.RestaurantDto
import com.foodapp.foodapp.features.home.data.dto.UserDto
import com.foodapp.foodapp.features.home.domain.models.Category
import com.foodapp.foodapp.features.home.domain.models.Restaurant
import com.foodapp.foodapp.features.home.domain.models.User

fun CategoryDto.toCategory():Category{
    return Category(
        id = id,
        name = name,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}

fun UserDto.toUser():User{
    return User(
        id = id,
        username = username,
        email = email,
       fullName = fullName
    )
}

fun RestaurantDto.toRestaurant():Restaurant{
    return Restaurant(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        distance = distance,
        createdAt = createdAt,
        imageUrl = imageUrl,
        user = user.toUser(),
        category = category.toCategory()
    )
}