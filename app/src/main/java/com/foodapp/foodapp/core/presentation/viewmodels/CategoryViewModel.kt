package com.foodapp.foodapp.core.presentation.viewmodels
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.core.domain.models.Category
import com.foodapp.foodapp.core.domain.usecass.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {

    var loadingCategories by mutableStateOf(false)
        private set

    var categories by mutableStateOf(emptyList<Category>())
        private set


    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            coreUseCases.getCategories().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d("Categories", resource.message)
                        loadingCategories = false
                    }

                    is Resource.Loading -> {
                        loadingCategories = resource.loading
                    }

                    is Resource.Success -> {
                        Log.d("Categories","${ resource.data}")
                        resource.data.let {
                            categories = it
                        }


                    }
                }
            }


        }

    }


}