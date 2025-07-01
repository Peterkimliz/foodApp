package com.foodapp.foodapp.features.auth.presentation.signup

sealed class SignUpEvents {
    data class EmailInput(val email:String): SignUpEvents()
    data class NameInput(val name:String): SignUpEvents()
    data class PasswordInput(val password:String): SignUpEvents()
    data object SignUpWithEmail: SignUpEvents()
    data object SignUpWithGoogle: SignUpEvents()
    data object ToggleLoginPassword: SignUpEvents()
    data object HideErrorDialog : SignUpEvents()
}