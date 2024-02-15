package com.amalitech.arms_mobile.domain.models

data class Staff(
    val name: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val position: String? = null,
    val type: String? = null,
    val image: String? = null,
)

