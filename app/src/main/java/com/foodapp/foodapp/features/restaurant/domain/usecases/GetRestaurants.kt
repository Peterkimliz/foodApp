package com.foodapp.foodapp.features.restaurant.domain.usecases

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.restaurant.domain.models.Restaurant
import com.foodapp.foodapp.features.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow

class GetRestaurants(
    private val restaurantRepository: RestaurantRepository
) {


    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<List<Restaurant>>> {
        return restaurantRepository.getRestaurants(latitude = latitude, longitude = longitude)
    }


}