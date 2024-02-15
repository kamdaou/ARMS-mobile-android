package com.amalitech.arms_mobile.ui.views.who_is_out

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.AppRoute
import com.amalitech.arms_mobile.ui.components.EmployeeDisplayCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView

object WhoIsOutRoute : AppRoute {
    override val path = "/who-is-out"
}

@Composable
fun WhoIsOutScreen() {
    ViewAllGridView(
        popAction = {},
        items = listOf("", "", "", "", "", "", "", "", "", "", "", ""),
        title = "Who's Out (23)",
        subtitle = "List of employees who will not be in the office",
        filterBuilder = {},
    ) { index, _ ->
        EmployeeDisplayCard(
            modifier = Modifier.padding(
                vertical = 12.dp, horizontal = dimensionResource(id = R.dimen.padding_small)
            ),
            image = "https://ca.slack-edge.com/T017QJT2H7G-U04EMHNTCQY-09ab99ea6d80-72",
            name = "Joshua Tetteh",
            position = "National Service Personnel",
            type = "Employee"
        )
    }
}