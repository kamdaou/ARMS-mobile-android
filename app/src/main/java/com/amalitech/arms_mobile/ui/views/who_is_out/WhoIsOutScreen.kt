package com.amalitech.arms_mobile.ui.views.who_is_out

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.AppRoute
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffDisplayCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView

object WhoIsOutRoute : AppRoute {
    override val path = "/who-is-out"
}

@Composable
fun WhoIsOutScreen(
    navController: NavController,
) {
    val viewModel: WhoIsOutViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    ViewAllGridView(
        popAction = {
            navController.popBackStack()
        },
        items = viewModel.data,
        title = "Who's Out (${viewModel.data.size})",
        subtitle = "List of employees who will not be in the office",
        loading = state.value.isLoading,
        loadingIndicator = {
            HorizontalShimmer(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
                    .fillMaxWidth()
            )
        },
        filterBuilder = {
           Box {
               Row {
                   Text("All")
                   Icon(
                       painter = painterResource(id = R.drawable.arrow_bottom),
                       contentDescription = null,
                   )
               }
           }
        },
    ) { _, item ->
        StaffDisplayCard(
            modifier = Modifier.padding(
                vertical = 12.dp, horizontal = dimensionResource(id = R.dimen.padding_small)
            ),
            image = item.image,
            name = item.name,
            position = item.position ?: "",
            type = item.type ?: ""
        )
    }
}