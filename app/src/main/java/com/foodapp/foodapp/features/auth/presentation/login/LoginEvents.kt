package com.foodapp.foodapp.features.auth.presentation.screens.login

sealed class LoginEvents {
 data class EmailInput(val email:String): LoginEvents()
 data class PasswordInput(val password:String): LoginEvents()
 data class Navigate(val route: String) : LoginEvents()
 data object LoginWithEmail: LoginEvents()
 data object SignUpWithGoogle: LoginEvents()
 data object ToggleLoginPassword: LoginEvents()
    data object  HideErrorDialog: LoginEvents()

}