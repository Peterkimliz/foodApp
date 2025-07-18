package com.foodapp.foodapp.core.domain.usecass

import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.core.domain.models.Category
import com.foodapp.foodapp.core.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow


class GetCategories (
    private val  categoryRepository: CategoryRepository
){

    suspend operator  fun invoke():Flow<Resource<List<Category>>>{
        return  categoryRepository.getCategories()
    }

}