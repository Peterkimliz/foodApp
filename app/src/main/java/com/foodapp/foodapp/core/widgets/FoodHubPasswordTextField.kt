package com.foodapp.foodapp.core.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun FoodHubPasswordTextField(
    showPassword: Boolean = false,
    validate:Boolean=false,
    value: String,
    modifier: Modifier = Modifier,
    onChange: (String) -> Unit,
    placeHolderText: String,
    labelText: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next),
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon,
            placeholder = {
                Text(text = placeHolderText)
            },
            label = {
                Text(text = labelText)
            },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onChange,
            supportingText = {
                if(validate){
                    Text(text = "$labelText is required")
                }

            },
            isError = validate

        )
    }

}







