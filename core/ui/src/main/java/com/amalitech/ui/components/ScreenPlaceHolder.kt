package com.amalitech.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.domain.R

@Preview(showBackground = true)
@Composable
fun ScreenPlaceHolder() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(110.dp),
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Working on",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text(
                text = "something great",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}