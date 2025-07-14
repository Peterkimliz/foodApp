package com.foodapp.foodapp.features.auth.domain.usecases

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow


class LoginWithEmailAndPassword (
   private val authRepository: AuthRepository
){

    suspend operator fun invoke(email:String,password:String): Flow<Resource<User>> {
        return authRepository.loginUser(email=email,password=password)
    }

}