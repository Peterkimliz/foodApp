package com.foodapp.foodapp.features.home.data.remote

import com.foodapp.foodapp.features.home.data.dto.RestaurantResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {

    @GET("restaurant/all")
    suspend fun getRestaurants(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): Response<RestaurantResponse>

}