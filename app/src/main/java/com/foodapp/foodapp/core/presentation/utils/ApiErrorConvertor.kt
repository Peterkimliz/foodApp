package com.foodapp.foodapp.core.presentation.utils

import android.util.Log
import com.foodapp.foodapp.features.auth.data.dtos.ApiError
import com.google.gson.Gson

object ApiErrorConvertor {
    fun convertError(errorBody: String?=null): ApiError {

        Log.d("Api Error Is ",errorBody.toString())

        val gson = Gson()
        return if (errorBody==null){
            ApiError(message = "Unknown error", statusCode = 404)
        }else{
            gson.fromJson(errorBody.toString(), ApiError::class.java)
        }


    }

}