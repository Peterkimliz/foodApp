package com.foodapp.foodapp.features.restaurant.di

import com.foodapp.foodapp.features.restaurant.data.datasource.remote.RestaurantApi
import com.foodapp.foodapp.features.restaurant.domain.repository.RestaurantRepository
import com.foodapp.foodapp.features.restaurant.domain.usecases.FetchRestaurantFoodItems
import com.foodapp.foodapp.features.restaurant.domain.usecases.GetRestaurants
import com.foodapp.foodapp.features.restaurant.domain.usecases.RestaurantUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantModule {


    @Provides
    @Singleton
    fun providesRestaurantApi(retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRestaurantUseCases(restaurantRepository: RestaurantRepository): RestaurantUseCases {
        return RestaurantUseCases(
            getRestaurants = GetRestaurants(
                restaurantRepository = restaurantRepository
            ),
            fetchRestaurantFoodItems = FetchRestaurantFoodItems(
                repository = restaurantRepository
            )
        )
    }
}