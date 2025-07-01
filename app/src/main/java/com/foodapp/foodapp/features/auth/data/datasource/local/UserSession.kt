package com.foodapp.foodapp.features.auth.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.foodapp.foodapp.features.auth.data.dtos.AuthResponse
import com.foodapp.foodapp.features.auth.data.dtos.UserDto


class UserSession(val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("foodhub", Context.MODE_PRIVATE)

    fun saveUserToSharedPreference(fullName: String, email: String, token: String, id: Int) {
        saveToken(token)
        sharedPreferences.edit().putString("email", email).apply()
        sharedPreferences.edit().putString("fullName", fullName).apply()
        sharedPreferences.edit().putInt("id", id).apply()
    }

    fun getToken():String{
       return sharedPreferences.getString("token",null)?:""
    }

    private fun saveToken(token:String){
       return sharedPreferences.edit().putString("token", token).apply()
    }

    fun getUserDetails(): UserDto {
        val fullName = sharedPreferences.getString("fullName", null) ?: ""
        val email = sharedPreferences.getString("email", null) ?: ""
        val id = sharedPreferences.getInt("id", 0)
        val userDto = UserDto(
            email = email,
            fullName = fullName,
            id = id)
        return userDto
    }


}