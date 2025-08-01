package com.foodapp.foodapp.features.auth.login

sealed class LoginEvents {
 data class EmailInput(val email:String): LoginEvents()
 data class PasswordInput(val password:String): LoginEvents()
 data class Navigate<T>(val screen: T) : LoginEvents()
 data object LoginWithEmail: LoginEvents()
 data object SignUpWithGoogle: LoginEvents()
 data object ToggleLoginPassword: LoginEvents()
    data object  HideErrorDialog: LoginEvents()

}