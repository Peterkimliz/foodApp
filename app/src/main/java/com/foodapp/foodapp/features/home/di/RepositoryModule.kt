package com.foodapp.foodapp.features.home.di

import com.foodapp.foodapp.features.home.data.repository.CategoryRepositoryImpl
import com.foodapp.foodapp.features.home.data.repository.RestaurantRepositoryImpl
import com.foodapp.foodapp.features.home.domain.repository.CategoryRepository
import com.foodapp.foodapp.features.home.domain.repository.RestaurantRepository
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
        categoryRepositoryImpl: CategoryRepositoryImpl
    ):CategoryRepository

    @Binds
    @Singleton
    abstract fun provideRestaurantRepository(
        restaurantRepositoryImpl: RestaurantRepositoryImpl
    ):RestaurantRepository
}