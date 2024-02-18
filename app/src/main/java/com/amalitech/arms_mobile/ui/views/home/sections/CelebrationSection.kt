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
import com.amalitech.arms_mobile.core.utilities.DateTimeFormatter
import com.amalitech.arms_mobile.core.utilities.StringFormatter
import com.amalitech.arms_mobile.ui.components.ErrorDisplay
import com.amalitech.arms_mobile.ui.components.HorizontalListBuilder
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffCelebrationCard
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CelebrationSection(
    stateFlow: StateFlow<CelebrationUiState>,
    onReload: () -> Unit,
    navigateToView: () -> Unit,
) {
    val state = stateFlow.collectAsState()

    HorizontalListBuilder(
        title = "Celebrations",
        items = state.value.celebrations,
        error = state.value.hasError,
        loading = state.value.isLoading,
        onExpand = if(state.value.celebrations.isNotEmpty()) navigateToView else null,
        loadingIndicator = {
            HorizontalShimmer(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
                    .width(160.dp)
            )
        },
        emptyListBuilder = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    "No Celebrations",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xff818181),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                )
            }
        },
        errorBuilder = {
            ErrorDisplay(
                modifier = Modifier.height(120.dp)
            ) { onReload() }
        }
    ) { _, item ->
        val name = StringFormatter.Name(item.staff.name)

        StaffCelebrationCard(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_small))
                .width(180.dp),
            image = item.staff.image,
            name = name.parse(),
            date = DateTimeFormatter.getParsedDate(item.anniversary ?: item.birthday ?: "")
                ?: "Jan 24",
            staffType = item.staff.type ?: "Employee",
            type = if (item.anniversary == null) {
                "Happy Birthday"
            } else {
                "${DateTimeFormatter.anniversary(item.anniversary)} Anniversary"
            },
        )

    }
}