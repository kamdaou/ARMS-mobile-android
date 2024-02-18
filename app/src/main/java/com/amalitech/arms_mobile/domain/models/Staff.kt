package com.amalitech.arms_mobile.domain.models

data class Staff(
    val id: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val position: String? = null,
    val type: String? = null,
    val image: String? = null,
)

