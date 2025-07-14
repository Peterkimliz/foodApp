package com.foodapp.foodapp.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.foodapp.foodapp.R
import com.foodapp.foodapp.features.home.domain.models.Category
import java.util.Locale

@Composable
fun CategoryItem(
    category: Category
) {
    Column(
        modifier = Modifier
            .clickable { }
            .width(70.dp)
            .padding(8.dp)

            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(45.dp)
            )
            .background(color = colorResource(R.color.white))
            .clip(RoundedCornerShape(45.dp))
            .padding(5.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        AsyncImage(
            model = category.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(40.dp)
                .clip(shape = CircleShape),
        )
        Spacer(Modifier.height(3.dp))

        Text(

            text = category.name.capitalize(Locale.ROOT),
            style = TextStyle(
                fontSize = 10.sp
            ),
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )

    }

}