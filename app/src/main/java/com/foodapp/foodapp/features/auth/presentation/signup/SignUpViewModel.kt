package com.foodapp.foodapp.features.auth.presentation.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.utils.Resource
import com.foodapp.foodapp.features.auth.domain.usecases.AuthUseCases
import com.foodapp.foodapp.features.auth.presentation.screens.signup.SignUpEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val authUseCases: AuthUseCases
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

    fun onEvent(event: SignUpEvents) {
        when (event) {
            is SignUpEvents.EmailInput -> {
                email=event.email
            }
            is SignUpEvents.NameInput -> {
                fullName=event.name
            }
            is SignUpEvents.PasswordInput -> {
                password=event.password
            }
            SignUpEvents.SignUpWithEmail -> {
                signUpWithEmail()
            }
            SignUpEvents.SignUpWithGoogle -> {}
            SignUpEvents.ToggleLoginPassword -> {
                obscurePassword=!obscurePassword
            }

            else -> {}
        }
    }

    private fun signUpWithEmail() {

        viewModelScope.launch {
            if (fullName.trim().isEmpty()){
                return@launch
            }
            if (email.trim().isEmpty()){
                return@launch
            }
            if (password.trim().isEmpty()){
                return@launch
            }

            authUseCases.signUpWithEmailAndPassword(
                email=email, password = password, fullName = fullName
            ).collectLatest {
                resource->
                when(resource){
                    is Resource.Error -> {
                        loading=false
                        Log.d("Error",resource.message)
                    }
                    is Resource.Loading -> {
                        loading=resource.loading
                    }
                    is Resource.Success -> {
                        Log.d("Error",resource.data.toString())
                    }
                }
            }

        }

    }


}