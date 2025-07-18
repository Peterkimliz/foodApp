package com.foodapp.foodapp.features.restaurant.presentation.restaurantdetails
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodapp.foodapp.core.presentation.utils.Resource
import com.foodapp.foodapp.features.restaurant.domain.models.FoodItem
import com.foodapp.foodapp.features.restaurant.domain.models.Restaurant
import com.foodapp.foodapp.features.restaurant.domain.usecases.RestaurantUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsScreenViewModel @Inject constructor(
    private val homeUseCases: RestaurantUseCases

) : ViewModel() {

    var loadingRestaurants by mutableStateOf(false)
        private set

    var restaurants by mutableStateOf(emptyList<Restaurant>())
        private set
    var loadingRestaurantsFoodItems by mutableStateOf(false)
        private set
    var foodItems by mutableStateOf(emptyList<FoodItem>())
        private set


    fun onEvent(event: RestaurantEvents) {
        when (event) {
            is RestaurantEvents.FetchFoodItems -> {
                getFoodItems(event.id)
            }
        }
    }

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
    private fun getFoodItems(id: Int) {
        viewModelScope.launch {
            homeUseCases.fetchRestaurantFoodItems(
                id = id
            ).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d("Food Items", resource.message)
                        loadingRestaurantsFoodItems = false
                    }

                    is Resource.Loading -> {
                        loadingRestaurantsFoodItems = resource.loading
                    }

                    is Resource.Success -> {
                        Log.d("Items Food", "${resource.data}")
                        resource.data.let {
                            foodItems = it
                        }


                    }
                }
            }


        }

    }


}