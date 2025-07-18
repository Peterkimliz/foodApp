package com.foodapp.foodapp.features.home.presentation.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.foodapp.foodapp.R
import com.foodapp.foodapp.features.restaurant.domain.models.Restaurant
import java.util.Locale

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable

fun RestaurantItem(
    item: Restaurant,
    onItemClick: (restaurant: Restaurant) -> Unit,
    animatedContentScope: SharedTransitionScope
) {
    Card(
        elevation = CardDefaults.cardElevation(
            3.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(200.dp)
            .width(280.dp)
            .padding(horizontal = 5.dp)
            .clickable {
                onItemClick(item)

            }


    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {

                    Row(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(15.dp)
                            )
                            .background(color = Color.White)
                            .padding(horizontal = 10.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "4.5",
                            style = MaterialTheme
                                .typography
                                .titleMedium
                                .copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                        )
                        Icon(
                            Icons.Filled.Star, contentDescription = null,
                            tint = Color(0xFFFA9602),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "(20)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 10.sp,
                                color = Color.Gray
                            )
                        )

                    }

                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.favorite),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }

            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp,
                            topStart = 0.dp,
                            topEnd = 0.dp
                        )
                    )
            ) {

                Text(
                    text = item.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.ic_delivery),
                            contentScale = ContentScale.Inside,
                            contentDescription = null
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = "Free Delivery",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        )
                    }

                    Spacer(Modifier.width(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(R.drawable.timer),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = "10-15 Minutes",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        )
                    }


                }


            }
        }
    }

}




