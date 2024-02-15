package com.amalitech.arms_mobile.ui.views.celebrations

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.AppRoute
import com.amalitech.arms_mobile.ui.components.EmployeeCelebrationCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView

object CelebrationRoute : AppRoute {
    override val path = "/celebration"
}

@Composable
fun CelebrationScreen(modifier: Modifier = Modifier) {
    ViewAllGridView(
        popAction = {},
        items = listOf("", "", "", "", "", "", "", "", "", "", "", ""),
        title = "Who's Out (23)",
        subtitle = "List of employees who will not be in the office",
        filterBuilder = {},
    ) { index, _ ->
        EmployeeCelebrationCard(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_small))
                .width(160.dp),
            image = "https://ca.slack-edge.com/T017QJT2H7G-U04EMHNTCQY-09ab99ea6d80-72",
            name = "Precious Ainoo",
            date = "Jan 24",
            type = if (index % 2 == 0) "Happy Birthday" else "1st Anniversary",
        )
    }

//
//    LazyVerticalGrid(
//        modifier = modifier
//            .statusBarsPadding()
//            .navigationBarsPadding(),
//        columns = GridCells.Fixed(2),
//        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small))
//    ) {
//        item(span = { GridItemSpan(2) }) {
//            Box(contentAlignment = Alignment.CenterStart) {
//                Icon(
//                    modifier = Modifier
//                        .padding(dimensionResource(id = R.dimen.padding_small))
//                        .size(dimensionResource(id = R.dimen.padding_large))
//                        .clickable { },
//                    painter = painterResource(id = R.drawable.arrow_left),
//                    contentDescription = null,
//
//                    )
//            }
//
//        }
//        item(span = { GridItemSpan(2) }) {
//            Column {
//                Text(
//                    text = "Who's out (23)",
//                    style = MaterialTheme.typography.headlineMedium,
//                    modifier = Modifier
//                        .padding(top = dimensionResource(id = R.dimen.padding_small))
//                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
//                )
//                Text(
//                    text = "List of employees who will not be in the office.",
//                    style = MaterialTheme.typography.titleSmall.copy(
//                        fontWeight = FontWeight.Normal, fontSize = 14.sp
//                    ),
//                    modifier = Modifier
//                        .padding(bottom = dimensionResource(id = R.dimen.padding_small))
//                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
//                )
//            }
//        }
//        item(span = { GridItemSpan(2) }) {
//            Box(
//                modifier = Modifier.padding(8.dp),
//                contentAlignment = Alignment.CenterStart,
//            ) {
//                Row(
//                    modifier = Modifier
//                        .wrapContentWidth()
//                        .border(
//                            width = 1.dp,
//                            color = Color(0xffB0B0B0),
//                            shape = RoundedCornerShape(8.dp)
//                        )
//                        .padding(horizontal = 16.dp, vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "All"
//                    )
//                    Spacer(modifier.width(16.dp))
//                    Icon(
//                        modifier = Modifier
//                            .size(dimensionResource(id = R.dimen.padding_medium))
//                            .clickable { },
//                        painter = painterResource(id = R.drawable.arrow_bottom),
//                        contentDescription = null,
//                        tint = Color(0xff696969)
//                    )
//                }
//            }
//        }
//        items(7) {
//            EmployeeDisplayCard(
//                modifier = Modifier.padding(
//                    vertical = 12.dp, horizontal = dimensionResource(id = R.dimen.padding_small)
//                ),
//                image = if (it % 2 == 1) "https://ca.slack-edge.com/T017QJT2H7G-U04BD8S7XH7-e72d73d981a8-512" else "https://ca.slack-edge.com/T017QJT2H7G-U064KN73751-f66d78ac27f9-512",
//                name = "Joshua Tetteh",
//                position = "National Service Personnel",
//                type = "Employee"
//            )
//        }
//    }
}
