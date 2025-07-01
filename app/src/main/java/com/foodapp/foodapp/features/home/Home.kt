package com.foodapp.foodapp.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Home(navHostController: NavHostController) {
    Scaffold {
        innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ){

       Box(){
           Text("Home")
       }

        }

    }
}