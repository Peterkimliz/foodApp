package com.foodapp.foodapp.features.restaurant.di

import com.foodapp.foodapp.core.data.repositories.CategoryRepositoryImpl
import com.foodapp.foodapp.core.domain.repositories.CategoryRepository
import com.foodapp.foodapp.features.restaurant.data.repository.RestaurantRepositoryImpl
import com.foodapp.foodapp.features.restaurant.domain.repository.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideCategoryRepository(
        repositoryImpl: RestaurantRepositoryImpl
    ): RestaurantRepository



}