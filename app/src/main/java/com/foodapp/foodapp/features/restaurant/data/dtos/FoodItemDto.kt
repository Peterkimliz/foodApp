package com.foodapp.foodapp.features.restaurant.data.dtos

data class FoodItemDto(
    val id:Int,
    val name:String,
    val description:String,
    val price:Double,
    val imageUrl:String,
    val createdAt:String,
    val restaurantId:Int

)
