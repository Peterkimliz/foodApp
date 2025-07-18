package com.foodapp.foodapp.features.restaurant.presentation.restaurantdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.foodapp.foodapp.R


@Composable
fun RestaurantDetailScreen(
    navHostController: NavHostController,
    viewModel: RestaurantDetailsScreenViewModel = hiltViewModel(),
    imageUrl: String,
    restaurantId: Int,
    title: String
) {
    viewModel.onEvent(
        RestaurantEvents.FetchFoodItems
            (id = restaurantId)
    )
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .shadow(
                                    elevation = 30.dp,
                                    ambientColor = Color.LightGray,
                                    spotColor = Color.LightGray,

                                    )
                                .clip(
                                    RoundedCornerShape(
                                        bottomStart = 20.dp,
                                        bottomEnd = 20.dp
                                    )
                                ),

                            contentScale = ContentScale.Crop

                        )


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .align(Alignment.TopStart)


                        ) {
                            Image(
                                painter = painterResource(R.drawable.back),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable {
                                        navHostController.popBackStack()
                                    }
                            )

                        }

                    }
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = title, style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }


            }

            item {
                if (viewModel.loadingRestaurantsFoodItems)
                    Box(
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
            }



        }

    }


}