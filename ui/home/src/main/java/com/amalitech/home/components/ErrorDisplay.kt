package com.amalitech.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp


@Composable
fun ErrorDisplay(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            "Error occurred...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xff818181),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            ),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Try Again",
            modifier = Modifier.clickable { onClick() },
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xddDD5928),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            ),
        )

    }
}