package com.foodapp.foodapp.ui.features.auth.onboard

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.foodapp.foodapp.R
import com.foodapp.foodapp.core.navigation.AppNavigationRoutes
import com.foodapp.foodapp.ui.features.auth.components.SocialLoginCard


@Composable
fun OnBoardScreen(
    navHostController: NavHostController,
    viewModel: OnBoardViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true) {
        viewModel.channel.collect { event ->
            if (event.token.trim().isNotEmpty()) {

                navHostController.navigate(AppNavigationRoutes.Home.route)

            }
        }


    }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        Image(
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Button(
                        modifier = Modifier.padding(10.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 10.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.white)
                        ),
                        onClick = {}) {
                        Text(
                            text = stringResource(R.string.skip),
                            color = colorResource(R.color.primary_color)
                        )
                    }
                }

                Spacer(Modifier.height(100.dp))
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(Modifier.height(5.dp))
                Text(
                    text = stringResource(R.string.food_hub),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.primary_color)
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = stringResource(R.string.your_favourite),

                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 23.sp,
                        color = Color.LightGray,
                        lineHeight = 30.sp
                    )
                )
            }


            Column {

                SocialLoginCard(onFacebookLogin = {}, onGoogleLogin = {})
                Spacer(Modifier.height(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White,

                        ),
                    border = BorderStroke(width = 2.dp, color = Color.White),
                    onClick = {
                        navHostController.navigate(AppNavigationRoutes.SignUp.route)

                    }
                ) {

                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),

                        text = stringResource(R.string.start_with)
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.have_account),
                        color = Color.White
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        modifier = Modifier.clickable {
                            navHostController.navigate(AppNavigationRoutes.Login.route)
                        },
                        text = stringResource(R.string.sign_in),
                        textDecoration = TextDecoration.Underline,
                        color = Color.White
                    )

                }
                Spacer(Modifier.height(10.dp))
            }


        }


    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnBoardScreenPreview() {

    OnBoardScreen(navHostController = rememberNavController())

}