package com.foodapp.foodapp.domain.usecases

import com.foodapp.foodapp.domain.models.User
import com.foodapp.foodapp.domain.repository.AuthRepository

class GetUserFromLocalStorage(
   private val authRepository: AuthRepository
) {


    suspend operator fun invoke(): User {
        return authRepository.getUserFromLocalStorage();

    }
}