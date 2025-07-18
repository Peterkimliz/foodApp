package com.foodapp.foodapp.features.restaurant.data.repository

import coil.network.HttpException
import com.foodapp.foodapp.core.presentation.utils.ApiErrorConvertor.convertError
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.auth.data.dtos.ApiError
import com.foodapp.foodapp.features.restaurant.data.mappers.toRestaurant
import com.foodapp.foodapp.features.restaurant.data.datasource.remote.RestaurantApi
import com.foodapp.foodapp.features.restaurant.data.mappers.toFoodItem
import com.foodapp.foodapp.features.restaurant.domain.models.FoodItem
import com.foodapp.foodapp.features.restaurant.domain.models.Restaurant
import com.foodapp.foodapp.features.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi
) : RestaurantRepository {

    override suspend fun getRestaurants(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<List<Restaurant>>> {

        return flow {
            emit(Resource.Loading(loading = true))
            val response = try {
                api.getRestaurants(latitude = latitude, longitude = longitude)
            } catch (e: IOException) {
                emit(Resource.Error(message = e.message.toString()))
                return@flow

            } catch (e: HttpException) {
                emit(Resource.Error(message = e.message.toString()))
                return@flow

            } catch (e: Exception) {
                emit(Resource.Error(message = e.message.toString()))
                return@flow
            }

            emit(Resource.Loading(false))
            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()!!.data.map { e -> e.toRestaurant() }))

            } else {
                val apiError: ApiError = convertError(response.errorBody()?.string())
                emit(Resource.Error(message = apiError.message))
            }


        }
    }

    override suspend fun fetchRestaurantFoodItems(restId: Int): Flow<Resource<List<FoodItem>>> {
        return flow {
            emit(Resource.Loading(true))
            val response = try {
                api.getFoodItem(restId)
            } catch (e: HttpException) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))
                return@flow
            } catch (e: IOException) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))
                return@flow
            }

            if (response.isSuccessful) {
                emit(Resource.Loading(false))
                emit(Resource.Success(data = response.body()!!.data.map { e -> e.toFoodItem() }))

            } else {
                val apiError: ApiError = convertError(response.errorBody()?.string())
                emit(Resource.Error(message = apiError.message))
            }


        }


        TODO("Not yet implemented")
    }
}