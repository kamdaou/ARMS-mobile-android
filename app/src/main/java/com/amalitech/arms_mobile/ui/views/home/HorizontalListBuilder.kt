package com.amalitech.arms_mobile.ui.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> HorizontalListBuilder(
    modifier: Modifier = Modifier,
    title: String,
    items: List<T> = emptyList<T>(),
    onExpand: () -> Unit = {},
    builder: @Composable (index: Int, item: T) -> Unit,
) {
    Column {
        Row {
            Text(text = title)
            Text(text = "View all", modifier = Modifier.clickable(onClick = onExpand))
        }
        LazyRow {
            items(items.size) {
                builder(it, items[it])
            }
        }

    }
}