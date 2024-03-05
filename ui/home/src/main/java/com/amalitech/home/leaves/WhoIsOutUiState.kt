package com.amalitech.home.leaves

import com.amalitech.home.models.Staff

data class WhoIsOutUiState(
    val today: List<Staff> = listOf(),
    val tomorrow: List<Staff> = listOf(),
    val checkedList: List<String> = listOf("Today", "Tomorrow"),
    val excludeTomorrow: Boolean = false,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)
