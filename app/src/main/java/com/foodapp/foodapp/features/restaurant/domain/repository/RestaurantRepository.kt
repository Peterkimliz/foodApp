package com.foodapp.foodapp.features.restaurant.domain.repository

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.restaurant.domain.models.FoodItem
import com.foodapp.foodapp.features.restaurant.domain.models.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getRestaurants(latitude:Double,longitude:Double):Flow<Resource<List<Restaurant>>>

    suspend fun  fetchRestaurantFoodItems(restId:Int):Flow<Resource<List<FoodItem>>>
}