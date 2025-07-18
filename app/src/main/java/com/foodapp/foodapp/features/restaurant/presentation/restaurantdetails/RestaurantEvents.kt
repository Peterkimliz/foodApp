package com.foodapp.foodapp.features.restaurant.presentation.restaurantdetails

sealed  class RestaurantEvents {
    data class  FetchFoodItems(val id:Int): RestaurantEvents()


}