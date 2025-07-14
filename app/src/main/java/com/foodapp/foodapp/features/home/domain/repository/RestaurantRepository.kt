package com.foodapp.foodapp.features.home.domain.repository

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.home.domain.models.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getRestaurants(latitude:Double,longitude:Double):Flow<Resource<List<Restaurant>>>
}