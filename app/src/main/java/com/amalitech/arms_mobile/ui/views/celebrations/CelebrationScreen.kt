package com.amalitech.arms_mobile.ui.views.celebrations

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
import com.amalitech.arms_mobile.core.utilities.AppRoute
import com.amalitech.arms_mobile.core.utilities.DateTimeFormatter
import com.amalitech.arms_mobile.core.utilities.StringFormatter
import com.amalitech.arms_mobile.ui.components.CheckBoxListItemData
import com.amalitech.arms_mobile.ui.components.CheckboxListForm
import com.amalitech.arms_mobile.ui.components.DropdownField
import com.amalitech.arms_mobile.ui.components.HorizontalShimmer
import com.amalitech.arms_mobile.ui.components.StaffCelebrationCard
import com.amalitech.arms_mobile.ui.components.ViewAllGridView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelebrationScreen(
    navController: NavController,
) {
    val viewModel: CelebrationViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    ViewAllGridView(
        popAction = {
            navController.popBackStack()
        },
        loading = state.value.isLoading,
        itemsData = viewModel.filteredData,
//        key = { it.staff.id },
        title = "Celebrations (${viewModel.filteredData.size})",
        subtitle = "List of employees who are celebrating",
        emptyListBuilder = {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "No employee has a celebration",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xff818181),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                )
            }
        },
        filterBuilder = {
            val checkedList = state.value.checkedList

            DropdownField(
                text = if (checkedList.containsAll(listOf("Anniversary", "Birthday"))) {
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
    ) { item ->
        val date = DateTimeFormatter.getParsedDate(item.anniversary ?: item.birthday ?: "")

        StaffCelebrationCard(
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_small)),
            image = item.staff.image,
            name = StringFormatter.Name(item.staff.name).parse(),
            date = date ?: "Jan 22",
            staffType = item.staff.type ?: "Employee",
            type = if (item.anniversary == null) {
                "Happy Birthday"
            } else {
                "${DateTimeFormatter.anniversary(item.anniversary)} Anniversary"
                   },
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
                            values.containsAll(listOf("Anniversary", "Birthday"))
                        },
                        onChecked = { values, value ->
                            if (value) {
                                (values + listOf("Anniversary", "Birthday")).toSet().toList()
                            } else {
                                values - listOf("Anniversary", "Birthday")
                            }
                        }
                    ),
                    CheckBoxListItemData(
                        text = "Anniversary",
                        condition = { values ->
                            values.contains("Anniversary")
                        },
                        onChecked = { values, value ->
                            if (value) {
                                values + "Anniversary"
                            } else {
                                values - "Anniversary"
                            }
                        }
                    ),
                    CheckBoxListItemData(
                        text = "Birthday",
                        condition = { values ->
                            values.contains("Birthday")
                        },
                        onChecked = { values, value ->
                            if (value) {
                                values + "Birthday"
                            } else {
                                values - "Birthday"
                            }
                        }
                    )
                )
            )
        }
    }
}
