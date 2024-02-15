package com.amalitech.arms_mobile.ui.views.home.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.DateHelper
import com.amalitech.arms_mobile.ui.components.ErrorDisplay
import com.amalitech.arms_mobile.ui.components.HorizontalListBuilder
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.ImagePlaceholder
import com.amalitech.arms_mobile.ui.views.home.CelebrationUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CelebrationSection(stateFlow: StateFlow<CelebrationUiState>, onReload: () -> Unit) {
    val state = stateFlow.collectAsState()

    HorizontalListBuilder(
        title = "Celebrations",
        items = state.value.celebrations,
        error = state.value.hasError,
        loading = state.value.isLoading,
        loadingIndicator = { HorizontalShimmer() },
        emptyListBuilder = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    "No Anniversary",
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
    ) { i, item ->
        Column(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_small))
                .width(160.dp)
        ) {
            if (item.staff.image == null) {
                ImagePlaceholder()
            } else {
                AsyncImage(
                    model = item.staff.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .height(120.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            }
            Text(
                text = item.staff.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                ),
            )
            Text(
                text = item.staff.type ?: "Employee",
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.padding_small).div(
                        2
                    )
                ),
                text = if (item.anniversary != null) "Happy Birthday" else "1st Anniversary",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                text = DateHelper.getParsedDate(item.anniversary ?: item.birthday ?: "")
                    ?.joinToString(" ")
                    ?: "Jan 24",
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}