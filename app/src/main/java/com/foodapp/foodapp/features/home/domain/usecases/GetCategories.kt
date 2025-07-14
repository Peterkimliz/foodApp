package com.foodapp.foodapp.features.home.domain.usecases

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.home.domain.models.Category
import com.foodapp.foodapp.features.home.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow


class GetCategories (
    private val  categoryRepository: CategoryRepository
){

    suspend operator  fun invoke():Flow<Resource<List<Category>>>{
        return  categoryRepository.getCategories()
    }

}