package com.foodapp.foodapp.di

import com.foodapp.foodapp.core.widgets.utils.Constants.BASE_URL
import com.foodapp.foodapp.features.auth.domain.usecases.AuthUseCases
import com.foodapp.foodapp.features.auth.domain.usecases.LoginWithEmailAndPassword
import com.foodapp.foodapp.features.auth.domain.usecases.SignUpWithEmailAndPassword
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




}