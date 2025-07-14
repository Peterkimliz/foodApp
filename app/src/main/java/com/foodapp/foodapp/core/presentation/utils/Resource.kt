package com.foodapp.foodapp.core.presentation.utils

sealed class Resource<T>(
     data: T? = null,
     message: String? = null
) {
    class Loading<L>(val loading:Boolean=false): Resource<L>()
    class Error<E>(val data: E?=null, val message:String): Resource<E>(data = data, message = message)
    class Success<S>( val data: S): Resource<S>(data = data)

}


