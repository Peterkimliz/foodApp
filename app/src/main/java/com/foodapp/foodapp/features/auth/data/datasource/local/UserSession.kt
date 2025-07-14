package com.foodapp.foodapp.features.auth.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.foodapp.foodapp.features.auth.data.dtos.UserDto

class UserSession (context: Context){
    private  val sharedPreference:SharedPreferences=context.getSharedPreferences("foody",Context.MODE_PRIVATE)

    fun saveUserDetails(token:String,fullName:String,email:String,id:Int){
        saveToken(token)
        sharedPreference.edit().putString("email",email).apply()
        sharedPreference.edit().putString("fullName",fullName).apply()
        sharedPreference.edit().putInt("id",id).apply()
    }


    fun getUserDetails(): UserDto {
       val email= sharedPreference.getString("email", null)?:""
       val id= sharedPreference.getInt("id",-1)
       val fullName= sharedPreference.getString("fullName", null)?:""

        return  UserDto(
            id = id,
            email = email,
            fullName = fullName
        )
    }

    private fun saveToken(token:String){
        sharedPreference.edit().putString("token",token).apply()
    }

    fun getToken():String{
        return  sharedPreference.getString("token", null)?:""
    }




}