package com.foodapp.foodapp.features.restaurant.data.datasource.remote

import com.foodapp.foodapp.features.restaurant.data.dtos.FoodItemResponse
import com.foodapp.foodapp.features.restaurant.data.dtos.RestaurantResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {

    @GET("restaurant/all")
    suspend fun getRestaurants(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): Response<RestaurantResponse>

    @GET("foodItem/all/{id}")
    suspend fun getFoodItem(@Path("id") id:Int):Response<FoodItemResponse>
}