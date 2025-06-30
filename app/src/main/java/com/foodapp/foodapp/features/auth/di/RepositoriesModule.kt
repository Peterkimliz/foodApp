package com.foodapp.foodapp.features.auth.di

import com.foodapp.foodapp.features.auth.data.repository.AuthRepositoryImpl
import com.foodapp.foodapp.features.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ):AuthRepository

}