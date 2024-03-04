package com.amalitech.home.home.sections

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.amalitech.home.celebrations.CelebrationUiState
import com.amalitech.home.components.ErrorDisplay
import com.amalitech.home.components.HorizontalListBuilder
import com.amalitech.home.components.HorizontalShimmer
import com.amalitech.home.components.StaffCelebrationCard
import com.amalitech.home.utils.DateTimeFormatter
import com.amalitech.home.utils.StringFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CelebrationSection(
    stateFlow: CelebrationUiState,
    onReload: () -> Unit,
    navigateToView: () -> Unit,
) {
    HorizontalListBuilder(
        title = "Celebrations",
        items = stateFlow.celebrations,
        error = stateFlow.hasError,
        loading = stateFlow.isLoading,
        onExpand = if (stateFlow.celebrations.isNotEmpty()) navigateToView else null,
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
        val anniversary = item.anniversary

        StaffCelebrationCard(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_small))
                .width(180.dp),
            image = item.staff.image,
            name = name.parse(),
            date = DateTimeFormatter.getParsedDate(item.anniversary ?: item.birthday ?: "")
                ?: "Jan 24",
            staffType = item.staff.type ?: "Employee",
            type = if (anniversary == null) {
                "Happy Birthday"
            } else {
                "${DateTimeFormatter.anniversary(anniversary)} Anniversary"
            },
        )

    }
}