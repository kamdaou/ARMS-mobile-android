package com.amalitech.arms_mobile.ui.views.home.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.StringFormatter
import com.amalitech.arms_mobile.ui.components.ErrorDisplay
import com.amalitech.arms_mobile.ui.components.HorizontalListBuilder
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffDisplayCard
import com.amalitech.arms_mobile.ui.views.home.LeaveUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WhoIsOutSection(
    stateFlow: StateFlow<LeaveUiState>,
    onReload: () -> Unit,
    navigateToView: () -> Unit,
) {
    val state = stateFlow.collectAsState()

    HorizontalListBuilder(
        title = "Who's out",
        items = state.value.leaves,
        error = state.value.hasError,
        loading = state.value.isLoading,
        onExpand = if(state.value.leaves.isNotEmpty()) navigateToView else null,
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



