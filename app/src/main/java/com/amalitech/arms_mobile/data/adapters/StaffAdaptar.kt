package com.amalitech.arms_mobile.data.adapters

import com.amalitech.arms_mobile.core.utilities.AdapterInterface
import com.amalitech.arms_mobile.data.entities.StaffEntity
import com.amalitech.arms_mobile.domain.models.Staff
import kotlinx.datetime.Clock

object StaffAdapter : AdapterInterface<StaffEntity, Staff> {
    override operator fun invoke(entity: StaffEntity): Staff {
//        val currentMoment = Clock.System.now()

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