package com.foodapp.foodapp.core.data.datasource.remote

import com.foodapp.foodapp.core.data.dtos.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("category/all")
    suspend fun getCategories(): Response<CategoriesResponse>
}