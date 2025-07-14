package com.foodapp.foodapp.features.auth.domain.usecases

import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository

class SaveUserToLocalStorage(
    private val authRepository: AuthRepository
) {


    suspend operator fun invoke(user: User) {
        authRepository.saveUserToLocalStorage(user = user)
    }


}