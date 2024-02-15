package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amalitech.arms_mobile.R


@Composable
fun EmployeeCelebrationCard(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    date: String,
    type: String,
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .height(120.dp)
                .width(160.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.padding_small).div(2),
            ),
        )
        Text(
            text = type,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.padding_small).div(2)
            ),
            text = type,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = date,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}