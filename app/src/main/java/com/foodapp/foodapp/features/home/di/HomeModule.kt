package com.foodapp.foodapp.features.home.di

import com.foodapp.foodapp.features.home.data.remote.CategoryApi
import com.foodapp.foodapp.features.home.data.remote.RestaurantApi
import com.foodapp.foodapp.features.home.domain.repository.CategoryRepository
import com.foodapp.foodapp.features.home.domain.repository.RestaurantRepository
import com.foodapp.foodapp.features.home.domain.usecases.GetCategories
import com.foodapp.foodapp.features.home.domain.usecases.GetRestaurants
import com.foodapp.foodapp.features.home.domain.usecases.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun providesCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRestaurantApi(retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }


    @Provides
    @Singleton
    fun providesHomeUseCase(
        categoryRepository: CategoryRepository,
        restaurantRepository: RestaurantRepository
    ): HomeUseCases {
        return HomeUseCases(
            getCategories = GetCategories(
                categoryRepository = categoryRepository
            ),

            getRestaurants = GetRestaurants(
                restaurantRepository = restaurantRepository
            )

        )
    }
}