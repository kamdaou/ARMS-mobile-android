package com.amalitech.arms_mobile.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.arms_mobile.R

import com.amalitech.arms_mobile.ui.components.OutlineBorderTextFormField

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreen() {

    Scaffold(
        modifier = Modifier
            .padding(
                horizontal = 18.dp
            )
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.amalitech_logo),
                    contentDescription = "arms logo",
                    modifier = Modifier.width(150.dp)
                )
            }
            Text(
                text = "Forgot your password",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = "Oops! \uD83D\uDE31 That's okay it happens, " +
                        "enter your email, we will send you instructions " +
                        "to reset your password.",

                )
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Email address")
            OutlineBorderTextFormField("hello@example.c")
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}