package com.foodapp.foodapp.features.auth.data.datasource.network

import com.foodapp.foodapp.features.auth.data.dtos.AuthResponse
import com.foodapp.foodapp.features.auth.data.dtos.LoginRequest
import com.foodapp.foodapp.features.auth.data.dtos.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

 @POST("auth/signin")
 suspend fun loginUser(@Body loginRequest: LoginRequest):Response<AuthResponse>

 @POST("auth/signup")
 suspend fun registerUser(@Body signUpRequest: SignUpRequest):Response<AuthResponse>





}