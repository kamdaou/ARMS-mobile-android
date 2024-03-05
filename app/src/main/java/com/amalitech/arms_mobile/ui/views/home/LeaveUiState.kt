package com.amalitech.arms_mobile.ui.views.home

import com.amalitech.home.models.Staff

data class LeaveUiState(
    val leaves: List<Staff> = listOf(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)