package com.foodapp.foodapp.core.di

import com.foodapp.foodapp.core.data.repositories.CategoryRepositoryImpl
import com.foodapp.foodapp.core.domain.repositories.CategoryRepository
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
    ): CategoryRepository

}