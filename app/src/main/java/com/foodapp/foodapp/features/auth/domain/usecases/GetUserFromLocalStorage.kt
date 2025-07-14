package com.foodapp.foodapp.features.auth.domain.usecases

import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository

class GetUserFromLocalStorage(
   private val authRepository: AuthRepository
) {


    suspend operator fun invoke(): User {
        return authRepository.getUserFromLocalStorage();

    }
}