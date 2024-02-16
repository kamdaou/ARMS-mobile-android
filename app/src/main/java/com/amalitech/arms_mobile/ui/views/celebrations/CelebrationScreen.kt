package com.amalitech.arms_mobile.ui.views.celebrations

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.AppRoute
import com.amalitech.arms_mobile.core.utilities.DateTimeFormatter
import com.amalitech.arms_mobile.core.utilities.StringFormatter
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffCelebrationCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView

object CelebrationRoute : AppRoute {
    override val path = "/celebration"
}

@Composable
fun CelebrationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val viewModel: CelebrationViewModel = hiltViewModel()
    val state = viewModel.celebrationState.collectAsState()

    ViewAllGridView(
        popAction = {
            navController.popBackStack()
        },
        loading = state.value.isLoading,
        items = state.value.celebrations,
        title = "Anniversaries (${state.value.celebrations.size})",
        subtitle = "List of employees who are celebrating anniversaries",
        filterBuilder = {},
        loadingIndicator = {
            HorizontalShimmer(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
                    .fillMaxWidth()
            )
        }
    ) { _, item ->
        val date = DateTimeFormatter.getParsedDate(item.anniversary ?: item.birthday ?: "")
            ?.joinToString(" ")

        StaffCelebrationCard(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_small)),
            image = item.staff.image,
            name = StringFormatter.Name(item.staff.name).parse(),
            date = date ?: "Jan 22",
            staffType = item.staff.type ?: "Employee",
            type = if (item.anniversary != null) "Happy Birthday" else "1st Anniversary",
        )
    }
}
