package com.amalitech.arms_mobile.data.models

import com.amalitech.arms_mobile.domain.entities.StaffEntity

data class Staff(override val name: String) : StaffEntity(name)
