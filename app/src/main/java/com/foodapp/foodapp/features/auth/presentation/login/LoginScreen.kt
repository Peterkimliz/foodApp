package com.foodapp.foodapp.features.auth.presentation.login

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
import com.foodapp.foodapp.features.auth.presentation.screens.login.LoginEvents

@Composable

fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val snackBarState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiState.collect { event ->
            when (event) {
                is UiEvents.Navigate -> {
                    navHostController.navigate(event.route)
                }
                UiEvents.PopBackStack -> {

                }
                is UiEvents.ShowSnackBar -> {
                    Log.d("Hello 1", event.message)
                    snackBarState.showSnackbar(
                        message = event.message
                    )


                }

                is UiEvents.ShowErrorDialog -> {}
            }


        }

    }


    Scaffold(
        Modifier.background(color = Color.Black),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState
            )

        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding))

        Box(
            modifier = Modifier
                .fillMaxSize()
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
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp),
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(30.dp))
                FoodHubTextField(
                    validate = viewModel.emailValidated,
                    value = viewModel.email,
                    onChange = {
                        viewModel.onEvent(
                            LoginEvents.EmailInput(it)
                        )
                    },
                    placeHolderText = "Email",
                    labelText = "Email"
                )
                Spacer(Modifier.height(30.dp))
                FoodHubPasswordTextField(
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    validate = viewModel.passwordValidated,
                    value = viewModel.password,

                    onChange = {
                        viewModel.onEvent(LoginEvents.PasswordInput(it))
                    },
                    placeHolderText = "Password",
                    labelText = "Password",
                    showPassword = viewModel.obscurePassword
                    )
                Spacer(Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.forgot_password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    color = colorResource(R.color.primary_color),
                    textAlign = TextAlign.End
                )
                Spacer(Modifier.height(30.dp))
                if (viewModel.loading) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    CustomButton(title = R.string.login, onClick = {
                        viewModel.onEvent(
                            LoginEvents.LoginWithEmail
                        )

                    })
                }

                Spacer(Modifier.height(30.dp))
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.dont_have_account),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        modifier = Modifier.clickable {
                            viewModel.onEvent(LoginEvents.Navigate(route=AppNavigationRoutes.SignUp.route))
                        },
                        text = stringResource(R.string.sign_up),
                        textDecoration = TextDecoration.Underline,
                        color = colorResource(R.color.primary_color),
                        style = MaterialTheme.typography.labelMedium,
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
fun LoginScreenPreview() {
    LoginScreen(navHostController = rememberNavController())
}