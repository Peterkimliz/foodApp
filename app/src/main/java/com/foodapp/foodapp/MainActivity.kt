package com.foodapp.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.foodapp.foodapp.core.navigation.AppNavigation
import com.foodapp.foodapp.ui.theme.FoodAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var showSplashScreen = true
        installSplashScreen().apply {

            setKeepOnScreenCondition { showSplashScreen }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FoodAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(Modifier.padding(innerPadding)){
                      AppNavigation()
                   }
                }


            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            showSplashScreen=false
        }
    }
}

