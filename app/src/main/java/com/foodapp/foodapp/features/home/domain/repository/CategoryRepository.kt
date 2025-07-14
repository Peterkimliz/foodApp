package com.foodapp.foodapp.features.home.domain.repository

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.home.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories():Flow<Resource<List<Category>>>

}