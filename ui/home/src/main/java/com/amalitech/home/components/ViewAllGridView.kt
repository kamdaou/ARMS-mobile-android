package com.amalitech.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.domain.R


@Composable
fun <T : Any> ViewAllGridView(
    modifier: Modifier = Modifier,
    popAction: () -> Unit,
    key: ((item: T) -> Any)? = null,
    loading: Boolean = false,
    loadingIndicator: @Composable () -> Unit,
    itemsData: List<T>,
    title: String,
    subtitle: String,
    emptyListBuilder: @Composable () -> Unit,
    filterBuilder: @Composable () -> Unit,
    itemBuilder: @Composable (item: T) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.padding_medium),
            vertical = dimensionResource(id = R.dimen.padding_small)
        )
    ) {
        item(span = { GridItemSpan(2) }) {
            Box(contentAlignment = Alignment.CenterStart) {
                Icon(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .size(dimensionResource(id = R.dimen.padding_large))
                        .clickable { popAction() },
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null,
                )
            }

        }
        item(span = { GridItemSpan(2) }) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small), end = 4.dp)
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal, fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .padding(
                            top = 4.dp,
                            bottom = dimensionResource(id = R.dimen.padding_medium)
                        )
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
        if (loading) {
            items(count = 6) { _ ->
                loadingIndicator()
            }
        } else {
            item(span = { GridItemSpan(2) }) {
                Box(
                    modifier = Modifier.padding(8.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    filterBuilder()
                }
            }

            if (itemsData.isEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    emptyListBuilder()
                }
            } else {
                items(itemsData, key = key) {
                    itemBuilder(it)
                }
            }
        }
    }
}