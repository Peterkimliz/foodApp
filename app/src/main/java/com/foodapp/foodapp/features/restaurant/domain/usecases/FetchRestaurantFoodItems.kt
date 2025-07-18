package com.foodapp.foodapp.features.restaurant.domain.usecases

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.restaurant.domain.models.FoodItem
import com.foodapp.foodapp.features.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow

class FetchRestaurantFoodItems(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke(id: Int): Flow<Resource<List<FoodItem>>> {
        return repository.fetchRestaurantFoodItems(id);
    }

}