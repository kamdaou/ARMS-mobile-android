package com.amalitech.arms_mobile.domain.entities

abstract class StaffEntity(
    open val name: String,
    open val firstName: String? = null,
    open val lastName: String? = null,
    open val position: String? = null,
)

