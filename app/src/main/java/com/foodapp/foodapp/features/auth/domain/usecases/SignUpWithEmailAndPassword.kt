package com.foodapp.foodapp.features.auth.domain.usecases

import com.foodapp.foodapp.core.utils.Resource
import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignUpWithEmailAndPassword (
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(email:String,password:String,fullName:String): Flow<Resource<User>> {
        return authRepository.signUpUser(email=email,password=password, fullName = fullName)
    }
}