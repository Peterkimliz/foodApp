package com.foodapp.foodapp.di

import android.app.Application
import com.foodapp.foodapp.core.utils.Constants.BASE_URL
import com.foodapp.foodapp.data.datasource.local.UserSession
import com.foodapp.foodapp.data.datasource.network.AuthApi
import com.foodapp.foodapp.domain.repository.AuthRepository
import com.foodapp.foodapp.domain.usecases.AuthUseCases
import com.foodapp.foodapp.domain.usecases.GetUserFromLocalStorage
import com.foodapp.foodapp.domain.usecases.LoginWithEmailAndPassword
import com.foodapp.foodapp.domain.usecases.SaveUserToLocalStorage
import com.foodapp.foodapp.domain.usecases.SignUpWithEmailAndPassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    }

    @Provides
    @Singleton
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesUserSession(application: Application): UserSession {
        return UserSession(
            context = application
        )
    }

    @Provides
    @Singleton
    fun providesAuthUseCases(authRepository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            loginWithEmailAndPassword = LoginWithEmailAndPassword(
                authRepository=authRepository
            ),
            signUpWithEmailAndPassword = SignUpWithEmailAndPassword(
                authRepository=authRepository
            ),
            saveUserToLocalStorage = SaveUserToLocalStorage(
                authRepository = authRepository
            ),
            getUserFromLocalStorage = GetUserFromLocalStorage(
                authRepository = authRepository
            )
        )

    }





}