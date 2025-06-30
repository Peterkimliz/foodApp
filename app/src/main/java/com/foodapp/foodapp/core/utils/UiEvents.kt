package com.foodapp.foodapp.core.utils

sealed  class UiEvents {

    data object PopBackStack:UiEvents();
    data class Navigate(val route:String ):UiEvents()
    data class ShowSnackBar(val message:String,val action:String?=null):UiEvents()
    data class ShowErrorDialog(val message:String):UiEvents()




}