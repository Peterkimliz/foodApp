package com.foodapp.foodapp.features.home.presentation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.foodapp.foodapp.R
import com.foodapp.foodapp.core.presentation.viewmodels.CategoryViewModel
import com.foodapp.foodapp.features.home.presentation.components.CategoryItem
import com.foodapp.foodapp.features.home.presentation.components.RestaurantItem
import com.foodapp.foodapp.features.restaurant.presentation.restaurantdetails.RestaurantDetailsScreenViewModel
import com.foodapp.foodapp.navigation.RestaurantDetail

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.Home(
    navHostController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
    restaurantViewModel: RestaurantDetailsScreenViewModel = hiltViewModel(),
    animatedContentScope: SharedTransitionScope

) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .height(20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)

        ) {
            if (viewModel.categories.isNotEmpty())
                Column {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.categories),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                    Spacer(Modifier.height(5.dp))
                    LazyRow {
                        itemsIndexed(items = viewModel.categories) { _, item ->
                            CategoryItem(category = item)
                        }
                    }
                }

            if (restaurantViewModel.restaurants.isNotEmpty())
                Column {
                    Spacer(Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.featured_restaurants),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )

                        Text(
                            text = stringResource(R.string.view_all),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(R.color.primary_color)
                            ),
                            modifier = Modifier.clickable {

                            }
                        )
                    }
                    Spacer(Modifier.height(15.dp))
                    LazyRow {
                        itemsIndexed(items = restaurantViewModel.restaurants) { _, item ->

                            RestaurantItem(
                                animatedContentScope=animatedContentScope,
                                item = item,
                                onItemClick = {
                                    navHostController
                                        .navigate(
                                            RestaurantDetail(
                                                imageUrl = item.imageUrl,
                                                title = item.name,
                                                restaurantId = item.id
                                            )
                                        )

                                }
                            )
                        }
                    }


                }

        }
    }


}



