package com.amalitech.arms_mobile.data.entities

import com.amalitech.GetCelebrationsQuery


data class CelebrationEntity(
    val userId: String,
    val name: String,
    val image: String,
    var birthDate: String?,
    val anniversaryDate: String?,
)

fun GetCelebrationsQuery.GetCelebration.toCelebrationEntity(): CelebrationEntity {
    var birthDate: String? = null
    var anniversaryDate: String? = null

    if(birthday != null) {
        birthDate = birthday.toString()
    }

    if(anniversary != null) {
        anniversaryDate = anniversary.toString()
    }

    return CelebrationEntity(
        userId = user_id?.toString() ?: "",
        name = full_name ?: "",
        image = profile_image ?: "",
        birthDate = birthDate,
        anniversaryDate = anniversaryDate,
    )
}


