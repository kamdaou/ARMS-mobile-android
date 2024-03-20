package com.amalitech.home.home.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amalitech.domain.R
import com.amalitech.home.components.ErrorDisplay
import com.amalitech.home.components.HorizontalListBuilder
import com.amalitech.home.components.HorizontalShimmer
import com.amalitech.home.components.StaffDisplayCard
import com.amalitech.home.home.LeaveUiState
import com.amalitech.home.utils.StringFormatter

@Composable
fun LeaveSection(
    stateFlow: LeaveUiState,
    onReload: () -> Unit,
    navigateToView: () -> Unit,
) {
    HorizontalListBuilder(
        title = "Who's out",
        items = stateFlow.leaves,
        error = stateFlow.hasError,
        loading = stateFlow.isLoading,
        onExpand = if (stateFlow.leaves.isNotEmpty()) navigateToView else null,
        loadingIndicator = {
            HorizontalShimmer(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small),
                    )
                    .width(160.dp)
            )
        },
        emptyListBuilder = {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "No employees is on leave",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xff818181),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                )
            }
        },
        errorBuilder = {
            ErrorDisplay(modifier = Modifier.height(120.dp)) { onReload() }
        }
    ) { _, item ->
        val name = StringFormatter.Name(item.name)

        StaffDisplayCard(
            modifier = Modifier
                .padding(
                    vertical = 12.dp,
                    horizontal = dimensionResource(id = R.dimen.padding_small)
                )
                .width(180.dp),
            image = item.image,
            name = name.parse(),
            position = item.position ?: "",
            type = item.type ?: ""
        )
    }
}


