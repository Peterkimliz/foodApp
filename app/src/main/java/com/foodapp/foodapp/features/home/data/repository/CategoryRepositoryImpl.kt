package com.foodapp.foodapp.features.home.data.repository

import android.util.Log
import coil.network.HttpException
import com.foodapp.foodapp.core.presentation.utils.ApiErrorConvertor.convertError
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.auth.data.dtos.ApiError
import com.foodapp.foodapp.features.home.data.mapper.toCategory
import com.foodapp.foodapp.features.home.data.remote.CategoryApi
import com.foodapp.foodapp.features.home.domain.models.Category
import com.foodapp.foodapp.features.home.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi
) : CategoryRepository {

    override suspend fun getCategories(): Flow<Resource<List<Category>>> {
        return flow {
            emit(Resource.Loading(true))

            val response = try {
                categoryApi.getCategories()
            } catch (e: HttpException) {
                emit(Resource.Error(message ="Http exception occurred"))
                return@flow
            } catch (e: IOException) {
                emit(Resource.Error(message = "Io exception occurred"))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message.toString()))
                return@flow
            }
            emit(Resource.Loading(false))
            Log.d("Categories","${response}")
            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()!!.data.map { category -> category.toCategory() }))
            } else {
                val apiError: ApiError = convertError(response.errorBody()?.string())
                emit(Resource.Error(message = apiError.message))
            }


        }
    }
}