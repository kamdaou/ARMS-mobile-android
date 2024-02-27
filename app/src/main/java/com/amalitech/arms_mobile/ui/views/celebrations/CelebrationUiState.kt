package com.amalitech.arms_mobile.ui.views.celebrations

import com.amalitech.arms_mobile.domain.models.Celebration

data class CelebrationUiState(
    val celebrations: List<Celebration> = listOf(),
    val checkedList: List<String> = listOf("Anniversary", "Birthday"),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)
