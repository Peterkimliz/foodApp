package com.foodapp.foodapp.core.presentation.utils

sealed  class UiEvents {

    data object PopBackStack: UiEvents();
    data class Navigate<T>(val route:T ): UiEvents()
    data class ShowSnackBar(val message:String,val action:String?=null): UiEvents()
    data class ShowErrorDialog(val message:String): UiEvents()




}