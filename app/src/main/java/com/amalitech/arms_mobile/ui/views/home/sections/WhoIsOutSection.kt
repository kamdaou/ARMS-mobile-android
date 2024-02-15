package com.amalitech.arms_mobile.ui.views.home.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.ui.components.ErrorDisplay
import com.amalitech.arms_mobile.ui.components.HorizontalListBuilder
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.ImagePlaceholder
import com.amalitech.arms_mobile.ui.views.home.LeaveUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WhoIsOutSection(
    stateFlow: StateFlow<LeaveUiState>,
    onReload: () -> Unit,
) {
    val state = stateFlow.collectAsState()

    HorizontalListBuilder(
        title = "Who's out",
        items = state.value.leaves,
        error = state.value.hasError,
        loading = state.value.isLoading,
        loadingIndicator = { HorizontalShimmer() },
        emptyListBuilder = {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "No Leaves",
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
        Column(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_small))
                .width(160.dp)
        ) {
            if (item.image == null) {
                ImagePlaceholder()
            } else {
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .height(120.dp)
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
                    text = item.position ?: "Null",
                    style = MaterialTheme.typography.labelLarge.copy(lineHeight = 0.sp),
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_small),
                        vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                    )
                )
            }
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                ),
            )
//            Text(
//                text = item.type ?: "",
//                style = MaterialTheme.typography.labelLarge,
//            )

        }
    }
}



