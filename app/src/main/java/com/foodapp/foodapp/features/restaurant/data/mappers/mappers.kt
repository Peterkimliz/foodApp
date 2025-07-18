package com.foodapp.foodapp.features.restaurant.data.mappers

import com.foodapp.foodapp.features.restaurant.data.dtos.FoodItemDto
import com.foodapp.foodapp.features.restaurant.data.dtos.RestaurantDto
import com.foodapp.foodapp.features.restaurant.domain.models.FoodItem
import com.foodapp.foodapp.features.restaurant.domain.models.Restaurant
import com.foodapp.foodapp.features.restaurant.domain.models.User



fun RestaurantDto.toRestaurant(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        distance = distance,
        createdAt = createdAt,
        imageUrl = imageUrl,
    )

}
 fun FoodItemDto.toFoodItem(): FoodItem {
     return  FoodItem(
         id=id,
         name=name,
         description = description,
         price = price,
         restaurantId = restaurantId,
         imageUrl = imageUrl,
         createdAt = createdAt
     )
 }

