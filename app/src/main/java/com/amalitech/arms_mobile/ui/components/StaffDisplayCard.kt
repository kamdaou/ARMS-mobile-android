package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.StringFormatter


@Composable
fun StaffDisplayCard(
    modifier: Modifier = Modifier,
    image: String?,
    name: String,
    position: String,
    type: String,
) {
    val parsedName = StringFormatter.Name(name)

    Column(
        modifier = modifier
    ) {
        if(image == null) {
            ImagePlaceholder(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                text = parsedName.initials()
            )
        } else {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    end = dimensionResource(id = R.dimen.padding_small).times(0.5F),
                )
                .clip(CircleShape)
                .border(width = 1.dp, color = Color(0xffE5EAEF), shape = CircleShape)
                .background(color = Color(0xffF5F7F9))
        ) {
            Text(
                text = position,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 0.sp),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_small),
                    vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                )
            )
        }
        Text(
            text = parsedName.parse(),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.padding_small).div(2),
            ),
        )
        Text(
            text = type,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}