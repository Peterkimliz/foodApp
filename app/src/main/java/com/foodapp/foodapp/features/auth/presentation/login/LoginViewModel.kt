package com.foodapp.foodapp.features.auth.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.core.presentation.utils.UiEvents
import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.usecases.AuthUseCases
import com.foodapp.foodapp.features.auth.login.LoginEvents
import com.foodapp.foodapp.navigation.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private  val authUseCases: AuthUseCases) : ViewModel() {
    var errorMessage by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var obscurePassword by mutableStateOf(false)
        private set
    var loading by mutableStateOf(false)
        private set

    var emailValidated by mutableStateOf(false)
        private set

    var passwordValidated by mutableStateOf(false)
        private set

    var showErrorDialog by mutableStateOf(false)
        private set


    private val _uiState = Channel<UiEvents>()
    val uiState = _uiState.receiveAsFlow()


    fun onEvent(event: LoginEvents) {
        when (event) {
            LoginEvents.LoginWithEmail -> {
                loginWithEmailAndPassword()
            }

            LoginEvents.SignUpWithGoogle -> {

            }

            LoginEvents.ToggleLoginPassword -> {
                obscurePassword = !obscurePassword
            }

            is LoginEvents.EmailInput -> {
                email = event.email
                emailValidated = email.trim().isEmpty()
            }

            is LoginEvents.PasswordInput -> {
                password = event.password
                passwordValidated = password.trim().isEmpty()
            }

            is LoginEvents.Navigate<*> -> {
                viewModelScope.launch {
                    _uiState.send(UiEvents.Navigate(event.screen))
                }

            }

            LoginEvents.HideErrorDialog -> {
                showErrorDialog = false
            }

            else -> {}
        }

    }

    private fun loginWithEmailAndPassword() {
        emailValidated = email.trim().isEmpty()
        passwordValidated = password.trim().isEmpty()

        viewModelScope.launch {
            if (emailValidated || passwordValidated) {
                return@launch
            }

            authUseCases.loginWithEmailAndPassword(
                email = email,
                password = password
            ).collectLatest {resource: Resource<User> ->
                when(resource){
                    is Resource.Error -> {
                        loading=false
                        showErrorDialog = true
                        errorMessage = resource.message
                    }
                    is Resource.Loading -> {
                        loading=resource.loading
                    }
                    is Resource.Success -> {
                        saveUserToLocalStorage(user = resource.data)
                     _uiState.send(UiEvents.Navigate(Home))
                    }
                }

            }

        }
    }

    private suspend fun saveUserToLocalStorage(user: User) {
        authUseCases.saveUserToLocalStorage(user = user)

    }


}