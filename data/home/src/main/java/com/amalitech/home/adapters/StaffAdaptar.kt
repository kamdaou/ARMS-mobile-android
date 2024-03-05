package com.amalitech.home.adapters

import com.amalitech.home.entities.StaffEntity
import com.amalitech.home.models.Staff

object StaffAdapter {
    operator fun invoke(entity: StaffEntity): Staff {

        return Staff(
            id = entity.id ?: "",
            name = entity.name ?: "",
            firstName = entity.firstName ?: "",
            lastName = entity.lastName ?: "",
            position = entity.position,
            type = entity.type,
            image = entity.image,
        )
    }
}