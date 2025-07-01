package com.foodapp.foodapp.features.auth.presentation.signup

import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.foodapp.foodapp.core.utils.UiEvents
import com.foodapp.foodapp.core.widgets.CustomButton
import com.foodapp.foodapp.core.widgets.FoodHubPasswordTextField
import com.foodapp.foodapp.core.widgets.FoodHubTextField
import com.foodapp.foodapp.features.auth.presentation.screens.components.SocialLoginCard
import com.foodapp.foodapp.features.auth.presentation.screens.signup.SignUpEvents

@Composable

fun SignUpScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val snackBarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }



    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is UiEvents.Navigate -> {
                    navHostController.navigate(event.route)
                }

                UiEvents.PopBackStack -> {}
                is UiEvents.ShowErrorDialog -> {}
                is UiEvents.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter =
                painterResource(id = R.drawable.ic_auth_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, start = 20.dp, end = 20.dp),
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(30.dp))
                FoodHubTextField(
                    value = viewModel.fullName,
                    onChange = {
                        viewModel.onEvent(SignUpEvents.NameInput(it))
                    },
                    placeHolderText = "FullName",
                    labelText = "Full Name"
                )
                Spacer(Modifier.height(10.dp))
                FoodHubTextField(
                    value = viewModel.email,
                    onChange = {
                        viewModel.onEvent(SignUpEvents.EmailInput(it))
                    },
                    placeHolderText = "Email",
                    labelText = "Email"
                )
                Spacer(Modifier.height(10.dp))
                FoodHubPasswordTextField(
                    value = viewModel.password,
                    onChange = {
                        viewModel.onEvent(SignUpEvents.PasswordInput(it))
                    },
                    showPassword = viewModel.obscurePassword,
                    placeHolderText = "Password",
                    labelText = "Password"
                )
                Spacer(Modifier.height(20.dp))

                if (viewModel.loading) {
                    Box(
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    CustomButton(title = R.string.sign_up, onClick = {
                        viewModel.onEvent(SignUpEvents.SignUpWithEmail)
                    })
                }

                Spacer(Modifier.height(30.dp))
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.have_account),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 18.sp

                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        modifier = Modifier.clickable {
                            navHostController.navigate(AppNavigationRoutes.Login.route)
                        },
                        text = stringResource(R.string.login),
                        style = MaterialTheme.typography.labelMedium,
                        textDecoration = TextDecoration.Underline,
                        color = colorResource(R.color.primary_color),
                        fontSize = 18.sp

                    )

                }
                Spacer(Modifier.height(30.dp))
                SocialLoginCard(
                    color = Color.Black,
                    onFacebookLogin = {},
                    onGoogleLogin = {}
                )
                Spacer(Modifier.height(20.dp))
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun SignUpScreenScreenPreview() {
    SignUpScreen(navHostController = rememberNavController())
}