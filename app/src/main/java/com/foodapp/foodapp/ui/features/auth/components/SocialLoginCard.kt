package com.foodapp.foodapp.ui.features.auth.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodapp.foodapp.R
import com.foodapp.foodapp.features.auth.components.SocialLoginButton

@Composable
fun SocialLoginCard(
    color: Color = Color.White,
    onFacebookLogin: () -> Unit,
    onGoogleLogin: () -> Unit,
) {


    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = color)
            Text(
                text = stringResource(R.string.sign_with),
                modifier = Modifier.padding(horizontal = 10.dp),
                color = color,
                textAlign = TextAlign.Center
                       , style = MaterialTheme.typography.labelMedium,
                fontSize = 16.sp
            )
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = color)
        }

        Spacer(Modifier.height(40.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SocialLoginButton(
                image = R.drawable.ic_facebook,
                title = R.string.facebook,
                callback = onFacebookLogin
            )

            SocialLoginButton(
                image = R.drawable.ic_google,
                title = R.string.google,
                callback = onGoogleLogin
            )
        }
    }
}