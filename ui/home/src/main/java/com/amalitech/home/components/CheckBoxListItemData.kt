package com.amalitech.home.components

data class CheckBoxListItemData(
    val text: String,
    val condition: (List<String>) -> Boolean,
    val onChecked: (List<String>, Boolean) -> List<String>
)
