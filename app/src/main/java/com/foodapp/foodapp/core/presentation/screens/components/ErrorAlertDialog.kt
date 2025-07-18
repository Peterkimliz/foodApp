package com.foodapp.foodapp.core.presentation.screens.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ErrorAlertDialog(
    title:String="Error",
    message:String,
    onDismiss:()->Unit
){
    AlertDialog(

        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = {

        },
        confirmButton = {

        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Okay")
            }
        }
    )
}