package com.foodapp.foodapp.domain.usecases

import com.foodapp.foodapp.domain.models.User
import com.foodapp.foodapp.domain.repository.AuthRepository

class SaveUserToLocalStorage(
    private val authRepository: AuthRepository
) {


    suspend operator fun invoke(user: User) {
        authRepository.saveUserToLocalStorage(user = user)
    }


}