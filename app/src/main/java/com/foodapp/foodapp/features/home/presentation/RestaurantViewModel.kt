package com.foodapp.foodapp.features.home.presentation
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.home.domain.models.Restaurant
import com.foodapp.foodapp.features.home.domain.usecases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    var loadingRestaurants by mutableStateOf(false)
        private set

    var restaurants by mutableStateOf(emptyList<Restaurant>())
        private set


    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            homeUseCases.getRestaurants(
                latitude = -4.000,
                longitude = 34.234546
            ).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d("Categories", resource.message)
                        loadingRestaurants = false
                    }

                    is Resource.Loading -> {
                        loadingRestaurants = resource.loading
                    }

                    is Resource.Success -> {
                        Log.d("Categories","${ resource.data}")
                        resource.data.let {
                            restaurants = it
                        }


                    }
                }
            }


        }

    }


}