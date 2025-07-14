package com.foodapp.foodapp.features.auth.data.repository

import android.util.Log
import coil.network.HttpException
import com.foodapp.foodapp.core.presentation.utils.ApiErrorConvertor.convertError
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.auth.data.datasource.local.UserSession
import com.foodapp.foodapp.features.auth.data.datasource.network.AuthApi
import com.foodapp.foodapp.features.auth.data.dtos.ApiError
import com.foodapp.foodapp.features.auth.data.dtos.LoginRequest
import com.foodapp.foodapp.features.auth.data.dtos.SignUpRequest
import com.foodapp.foodapp.features.auth.data.mapper.toUser
import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val userSession: UserSession
) : AuthRepository {
    override suspend fun loginUser(email: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(true))
            val response = try {
                api.loginUser(
                    LoginRequest(
                        email = email,
                        password = password,

                        )
                )
            } catch (e: IOException) {
                emit(Resource.Loading(false))
                Log.d("Data 1", e.toString())
                emit(Resource.Error(message = e.toString()))

                return@flow
            } catch (e: HttpException) {

                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.toString()))

                return@flow
            } catch (e: Exception) {
                emit(Resource.Loading(false))

                Log.d("Data 3", e.stackTraceToString())
                emit(Resource.Error(message = e.toString()))

                return@flow
            }
            emit(Resource.Loading(false))

            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()!!.toUser()))
            } else {
                val apiError: ApiError = convertError(response.errorBody()?.string())
                emit(Resource.Error(message =apiError.message))
            }


        }
    }

    override suspend fun signUpUser(
        email: String,
        password: String,
        fullName: String
    ): Flow<Resource<User>> {

        return flow {
            emit(Resource.Loading(true))
            val response = try {
                api.registerUser(
                    SignUpRequest(
                        email = email,
                        password = password,
                        fullName = fullName
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))

                return@flow
            } catch (e: HttpException) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))

                return@flow
            }
            emit(Resource.Loading(false))
            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()!!.toUser()))
            } else {
                val apiError: ApiError = convertError(response.errorBody()?.string())
                emit(Resource.Error(message =apiError.message))
            }

        }

    }

    override suspend fun signOut() {


    }

    override suspend fun saveUserToLocalStorage(user: User) {
        userSession.saveUserDetails(
            fullName = user.fullName,
            email = user.email,
            token = user.token,
            id = user.id
        )
    }

    override suspend fun getUserFromLocalStorage(): User {
        val token = userSession.getToken()
        val userDto = userSession.getUserDetails()
        return User(
            token = token,
            id = userDto.id,
            email = userDto.email,
            fullName = userDto.fullName
        )
    }



}