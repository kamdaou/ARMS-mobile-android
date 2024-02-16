package com.amalitech.arms_mobile.ui.views.who_is_out

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.AppRoute
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffDisplayCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView
import kotlinx.coroutines.launch

object WhoIsOutRoute : AppRoute {
    override val path = "/who-is-out"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhoIsOutScreen(
    navController: NavController,
) {
    val viewModel: WhoIsOutViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }


    ViewAllGridView(
        popAction = {
            navController.popBackStack()
        },
        items = viewModel.data,
        key = { viewModel.data[it].id },
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
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xffb0b0b0), shape = RoundedCornerShape(8.dp),
                    )
                    .clickable {
                        scope
                            .launch { sheetState.show() }
                            .invokeOnCompletion { showBottomSheet = true }
                    },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 12.dp,
                    )
                ) {
                    Log.d("APP:: ", state.value.checkedList.toString())
                    Text(
                        if (state.value.checkedList.size > 1) {
                            "All"
                        } else {
                            state.value.checkedList.first()
                        }, style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(16.dp))
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

//    if (showBottomSheet) {
        AppBottomSheet(selectedValues = state.value.checkedList, onChecked = {
            viewModel.onChecked(it)
            showBottomSheet = false;
        }, onDismissed = {
            showBottomSheet = false
        }, state = sheetState
        )
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBottomSheet(
    selectedValues: List<String>,
    state: SheetState,
    onChecked: (List<String>) -> Unit,
    onDismissed: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var values by remember {
        mutableStateOf(selectedValues)
    }

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                state.hide()
            }.invokeOnCompletion {
                onDismissed()
            }
        }, sheetState = state
    ) {
        Text(
            "Select your preferred options",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        CheckBoxListItem(
            text = "All",
            value = values.contains("All"),
            onChecked = {
                values = if (it) {
                    (values + listOf("All", "Today", "Tomorrow")).toSet().toList()
                } else {
                    values - "All"
                }
            },
        )
        CheckBoxListItem(
            text = "Today",
            value = values.contains("Today"),
            onChecked = {
                values = if (it) {
                    values + "Today"
                } else {
                    values - "Today"
                }
            },
        )
        CheckBoxListItem(
            text = "Tomorrow",
            value = values.contains("Tomorrow"),
            onChecked = {
                values = if (it) {
                    values + "Tomorrow"
                } else {
                    values - "Tomorrow"
                }
            },
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffDD5928),
                contentColor = Color.White,
            ),
            onClick = {
                scope.launch {
                    onChecked(values)
                    state.hide()
                }
            },
        ) {
            Text(
                "Update",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
private fun CheckBoxListItem(
    modifier: Modifier = Modifier,
    text: String,
    value: Boolean = false,
    onChecked: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text)
        Checkbox(
            checked = value, onCheckedChange = onChecked,
            colors = CheckboxDefaults.colors(
                checkmarkColor = Color.White, checkedColor = Color(0xffDD5928)
            ),
        )
    }
}