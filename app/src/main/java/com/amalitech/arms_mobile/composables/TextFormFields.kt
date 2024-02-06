package com.amalitech.arms_mobile.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.ui.theme.whiteSmoke

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TextFormField(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(whiteSmoke)
        .padding(16.dp) ) 
    {
        TextField(
            value = "", 
            onValueChange = {},
            placeholder = {
               Text(
                   text = "search basket........",
                   color = Color.Black
               )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.sms),
                    contentDescription = ""
                )
            },
            trailingIcon = {

            },
            colors = TextFieldDefaults.colors(
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}