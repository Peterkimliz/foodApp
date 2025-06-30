package com.foodapp.foodapp.features.auth.domain.repository

import com.foodapp.foodapp.core.utils.Resource
import com.foodapp.foodapp.features.auth.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(email:String, password:String):Flow<Resource<User>>
    suspend fun signUpUser(email:String, password:String,fullName:String):Flow<Resource<User>>
}