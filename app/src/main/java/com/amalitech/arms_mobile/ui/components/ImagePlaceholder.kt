package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.ui.theme.altGreyColor

@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier, text: String = "") {
    Box(
        modifier = modifier
            .background(color = Color(0xffefefef), shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = altGreyColor)
                .padding(16.dp),

            ) {
            Text(text = text, style = MaterialTheme.typography.headlineLarge)
        }
    }
}