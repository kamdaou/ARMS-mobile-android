package com.amalitech.arms_mobile.data.entities

import com.amalitech.GetCelebrationsQuery

data class CelebrationEntity(val name: String)

fun GetCelebrationsQuery.GetCelebration.toCelebrationEntity() : CelebrationEntity {
    return CelebrationEntity(name = full_name ?: "")
}


