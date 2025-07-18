package com.foodapp.foodapp.core.domain.repositories

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.core.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories():Flow<Resource<List<Category>>>

}