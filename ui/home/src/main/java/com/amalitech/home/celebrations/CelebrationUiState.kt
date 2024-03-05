package com.amalitech.home.celebrations

import com.amalitech.home.models.Celebration

data class CelebrationUiState(
    val celebrations: List<Celebration> = listOf(),
    val checkedList: List<String> = listOf("Anniversary", "Birthday"),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)
