package com.amalitech.home.adapters

import com.amalitech.home.entities.CelebrationEntity
import com.amalitech.home.models.Celebration
import com.amalitech.home.models.Staff

object CelebrationAdapter {
    operator fun invoke(entity: CelebrationEntity): Celebration {

        return Celebration(
            staff = Staff(
                name = entity.name.toString(),
                firstName = entity.name.toString(),
                lastName = entity.name.toString(),
                id = entity.userId ?: "",
                image = entity.image
            ),
            anniversary = entity.anniversaryDate,
            birthday = entity.birthDate
        )
    }
}