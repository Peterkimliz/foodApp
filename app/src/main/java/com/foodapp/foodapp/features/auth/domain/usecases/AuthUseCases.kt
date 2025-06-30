package com.foodapp.foodapp.features.auth.domain.usecases

data class AuthUseCases (
    val loginWithEmailAndPassword: LoginWithEmailAndPassword,
    val signUpWithEmailAndPassword: SignUpWithEmailAndPassword
)