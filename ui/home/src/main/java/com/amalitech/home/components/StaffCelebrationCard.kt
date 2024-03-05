package com.amalitech.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amalitech.domain.R
import com.amalitech.home.utils.StringFormatter


@Composable
fun StaffCelebrationCard(
    modifier: Modifier = Modifier,
    image: String?,
    name: String,
    date: String,
    staffType: String,
    type: String,
    hasType: Boolean = true
) {
    val parsedName = StringFormatter.Name(name)
    Column(
        modifier = modifier
    ) {
        if (image == null || image.trim().isEmpty()) {
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
        Text(
            text = parsedName.parse(),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.padding_small).div(2),
            ),
        )
        Text(
            text = staffType,
            style = MaterialTheme.typography.labelLarge,
        )
        if (hasType) Text(
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