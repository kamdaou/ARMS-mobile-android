package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.amalitech.arms_mobile.R

@Composable
fun <T> HorizontalListBuilder(
    modifier: Modifier = Modifier,
    title: String,
    items: List<T> = emptyList(),
    onExpand: () -> Unit = {},
    builder: @Composable (index: Int, item: T) -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_large)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
            Text(
                text = "View all",
                modifier = Modifier.clickable(onClick = onExpand),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color(0xffDD5928),
                    fontWeight = FontWeight.Normal,
                ),
            )
        }
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.padding_medium),
                vertical = dimensionResource(id = R.dimen.padding_small)
            )
        ) {
            items(items.size) {
                builder(it, items[it])
            }
        }

    }
}