package com.foodapp.foodapp.core.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FoodHubTextField(
    validate: Boolean = false,
    value: String,
    modifier: Modifier = Modifier,
    onChange: (String) -> Unit,
    placeHolderText: String,
    labelText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            keyboardOptions = keyboardOptions,
            placeholder = {
                Text(text = placeHolderText)
            },
            label = {
                Text(text = labelText)
            },

            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onChange,
            supportingText = {
                if (validate) {
                    Text(text = "$labelText is required")
                }

            },
            isError = validate

        )
    }

}







