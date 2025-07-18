package com.foodapp.foodapp.core.di

import com.foodapp.foodapp.core.data.datasource.remote.CategoryApi
import com.foodapp.foodapp.core.domain.repositories.CategoryRepository
import com.foodapp.foodapp.core.domain.usecass.CoreUseCases
import com.foodapp.foodapp.core.domain.usecass.GetCategories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDi {
    @Provides
    @Singleton
    fun providesCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoreUseCase(
        categoryRepository: CategoryRepository,
    ): CoreUseCases {
        return CoreUseCases(
            getCategories = GetCategories(
                categoryRepository = categoryRepository
            ),
        )
    }


}