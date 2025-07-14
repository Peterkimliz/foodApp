package com.foodapp.foodapp.features.auth.domain.models


data class User(
    val email:String,
    val fullName:String,
    val id:Int,
    val token:String,
)
