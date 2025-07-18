package com.foodapp.foodapp.core.data.mappers

import com.foodapp.foodapp.core.data.dtos.CategoryDto
import com.foodapp.foodapp.core.domain.models.Category

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}