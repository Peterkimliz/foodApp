package com.foodapp.foodapp.features.auth.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.domain.models.User
import com.foodapp.foodapp.domain.usecases.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    authUseCases: AuthUseCases
):ViewModel() {

    var _channel= Channel<User>()
    var channel=_channel.receiveAsFlow()

    init {
        viewModelScope.launch {
           val user= authUseCases.getUserFromLocalStorage()
            _channel.send(user)
        }

    }


}