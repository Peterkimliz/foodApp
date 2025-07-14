package com.foodapp.foodapp.features.home.data.repository

import coil.network.HttpException
import com.foodapp.foodapp.core.presentation.utils.ApiErrorConvertor.convertError
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.auth.data.dtos.ApiError
import com.foodapp.foodapp.features.home.data.mapper.toRestaurant
import com.foodapp.foodapp.features.home.data.remote.RestaurantApi
import com.foodapp.foodapp.features.home.domain.models.Restaurant
import com.foodapp.foodapp.features.home.domain.repository.RestaurantRepository
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
}