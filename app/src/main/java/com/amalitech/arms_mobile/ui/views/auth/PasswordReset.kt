package com.amalitech.arms_mobile.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.arms_mobile.R


@Preview(showBackground = true)
@Composable
fun PasswordRest() {

    Scaffold(
        modifier = Modifier
            .padding(horizontal = 18.dp
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
            Image(painter = painterResource(id = R.drawable.fi_1652598), contentDescription = "logo")
            Text(
                text = "Password reset\ninstructions sent!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = "We have sent an email to" +
                        "hello@example.com that contains a link to update your password."

                )
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = "Didn't receive the email?")

            }

        }

    }
}