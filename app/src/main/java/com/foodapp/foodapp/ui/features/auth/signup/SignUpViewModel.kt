package com.foodapp.foodapp.ui.features.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.navigation.AppNavigationRoutes
import com.foodapp.foodapp.core.utils.Resource
import com.foodapp.foodapp.core.utils.UiEvents
import com.foodapp.foodapp.domain.models.User
import com.foodapp.foodapp.domain.usecases.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var fullName by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var obscurePassword by mutableStateOf(false)
        private set


    var loading by mutableStateOf(false)
        private set

    var showErrorDialog by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf("")
        private set

    var emailNotValid by mutableStateOf(false)
        private set
    var passwordNotValid by mutableStateOf(false)
        private set

    var fullNameNotValid by mutableStateOf(false)
        private set

    private val _uiState=Channel<UiEvents>()
    val uiEvents=_uiState.receiveAsFlow()


    fun onEvent(event: SignUpEvents) {
        when (event) {
            is SignUpEvents.EmailInput -> {
                email=event.email
                emailNotValid = email.trim().isEmpty()
            }
            is SignUpEvents.NameInput -> {
                fullName=event.name
                fullNameNotValid = fullName.trim().isEmpty()
            }
            is SignUpEvents.PasswordInput -> {
                password=event.password
                passwordNotValid = password.trim().isEmpty()
            }
            SignUpEvents.SignUpWithEmail -> {
                signUpWithEmail()
            }
            SignUpEvents.SignUpWithGoogle -> {}
            SignUpEvents.ToggleLoginPassword -> {
                obscurePassword=!obscurePassword
            }

            SignUpEvents.HideErrorDialog -> {
                showErrorDialog = false
            }
        }
    }




    private fun signUpWithEmail() {
        emailNotValid = email.trim().isEmpty()
        passwordNotValid = password.trim().isEmpty()
        fullNameNotValid = fullName.trim().isEmpty()


        viewModelScope.launch {
            if (emailNotValid || passwordNotValid || fullNameNotValid) {
                return@launch
            }

            authUseCases.signUpWithEmailAndPassword(
                email=email, password = password, fullName = fullName
            ).collectLatest {
                resource->
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
                        _uiState.send(UiEvents.Navigate(route = AppNavigationRoutes.Home.route))
                    }
                }
            }

        }

    }

    private suspend fun saveUserToLocalStorage(user: User) {
        authUseCases.saveUserToLocalStorage(user = user)
    }


}