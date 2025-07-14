package com.foodapp.foodapp.features.home.data.remote

import com.foodapp.foodapp.features.home.data.dto.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("category/all")
    suspend fun getCategories(): Response<CategoriesResponse>
}