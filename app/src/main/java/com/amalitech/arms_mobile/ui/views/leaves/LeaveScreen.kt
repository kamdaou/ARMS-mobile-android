package com.amalitech.arms_mobile.ui.views.leaves

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.ui.components.CheckBoxListItemData
import com.amalitech.arms_mobile.ui.components.CheckboxListForm
import com.amalitech.arms_mobile.ui.components.DropdownField
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffDisplayCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView
import kotlinx.coroutines.launch

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
        itemsData = viewModel.filteredData,
//        key = { it.id },
        title = "Who's Out (${viewModel.filteredData.size})",
        subtitle = "List of employees who will not be in the office",
        loading = state.value.isLoading,
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
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                )
            }
        },
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
            val checkedList = state.value.checkedList
            DropdownField(
                text = if (checkedList.containsAll(listOf("Today", "Tomorrow"))) {
                    "All"
                } else {
                    checkedList.first()
                },
                onClick = {
                    scope
                        .launch { sheetState.show() }
                        .invokeOnCompletion { showBottomSheet = true }
                },
            )
        },
    ) { item ->
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

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    showBottomSheet = false
                }
            }, sheetState = sheetState
        ) {
            CheckboxListForm(
                selectedValues = state.value.checkedList,
                submitButtonText = "Update",
                submit = {
                    if (it.isNotEmpty()) {
                        scope.launch {
                            viewModel.updateCheckedList(it)
                            sheetState.hide()

                        }.invokeOnCompletion {
                            showBottomSheet = false
                        }
                    }
                },
                items = listOf(
                    CheckBoxListItemData(
                        text = "All",
                        condition = { values ->
                            values.containsAll(listOf("Today", "Tomorrow"))
                        },
                        onChecked = { values, value ->
                            if (value) {
                                (values + listOf("Today", "Tomorrow")).toSet().toList()
                            } else {
                                values - listOf("Today", "Tomorrow")
                            }
                        }
                    ),
                    CheckBoxListItemData(
                        text = "Today",
                        condition = { values ->
                            values.contains("Today")
                        },
                        onChecked = { values, value ->
                            if (value) {
                                values + "Today"
                            } else {
                                values - "Today"
                            }
                        }
                    ),
                    CheckBoxListItemData(
                        text = "Tomorrow",
                        condition = { values ->
                            values.contains("Tomorrow")
                        },
                        onChecked = { values, value ->
                            if (value) {
                                values + "Tomorrow"
                            } else {
                                values - "Tomorrow"
                            }
                        }
                    )
                )
            )
        }
    }
}

