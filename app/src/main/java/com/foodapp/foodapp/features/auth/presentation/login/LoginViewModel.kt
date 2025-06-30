package com.foodapp.foodapp.features.auth.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.navigation.AppNavigationRoutes
import com.foodapp.foodapp.core.utils.Resource
import com.foodapp.foodapp.core.utils.UiEvents
import com.foodapp.foodapp.features.auth.domain.models.User
import com.foodapp.foodapp.features.auth.domain.usecases.AuthUseCases
import com.foodapp.foodapp.features.auth.presentation.screens.login.LoginEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private  val authUseCases: AuthUseCases) : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var obscurePassword by mutableStateOf(false)
        private set
    var loading by mutableStateOf(false)
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
            }

            is LoginEvents.PasswordInput -> {
                password = event.password
            }

            is LoginEvents.Navigate -> {
                viewModelScope.launch {
                    _uiState.send(UiEvents.Navigate(event.route))
                }

            }
        }

    }

    private fun loginWithEmailAndPassword() {

        viewModelScope.launch {
            if (email.trim().isEmpty()) {
                return@launch
            }
            if (password.trim().isEmpty()) {
                return@launch
            }

            authUseCases.loginWithEmailAndPassword(
                email = email,
                password = password
            ).collectLatest {resource: Resource<User> ->
                when(resource){
                    is Resource.Error -> {
                        loading=false

                        _uiState.send(UiEvents.ShowSnackBar(message = resource.message))
                    }
                    is Resource.Loading -> {
                        loading=resource.loading
                    }
                    is Resource.Success -> {
                     _uiState.send(UiEvents.Navigate(route = AppNavigationRoutes.Home.route))
                    }
                }

            }

        }
    }


}