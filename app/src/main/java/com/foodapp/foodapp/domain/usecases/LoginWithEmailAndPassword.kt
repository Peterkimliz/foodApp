package com.foodapp.foodapp.domain.usecases

import com.foodapp.foodapp.core.utils.Resource
import com.foodapp.foodapp.domain.models.User
import com.foodapp.foodapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow


class LoginWithEmailAndPassword (
   private val authRepository: AuthRepository
){

    suspend operator fun invoke(email:String,password:String): Flow<Resource<User>> {
        return authRepository.loginUser(email=email,password=password)
    }

}