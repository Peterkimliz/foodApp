package com.foodapp.foodapp.features.auth.di

import android.app.Application
import com.foodapp.foodapp.features.auth.data.datasource.local.UserSession
import com.foodapp.foodapp.features.auth.data.datasource.network.AuthApi
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository
import com.foodapp.foodapp.features.auth.domain.usecases.AuthUseCases
import com.foodapp.foodapp.features.auth.domain.usecases.GetUserFromLocalStorage
import com.foodapp.foodapp.features.auth.domain.usecases.LoginWithEmailAndPassword
import com.foodapp.foodapp.features.auth.domain.usecases.SaveUserToLocalStorage
import com.foodapp.foodapp.features.auth.domain.usecases.SignUpWithEmailAndPassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


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