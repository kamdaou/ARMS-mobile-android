package com.amalitech.arms_mobile.data.adapters

import com.amalitech.arms_mobile.core.utilities.AdapterInterface
import com.amalitech.arms_mobile.data.entities.CelebrationEntity
import com.amalitech.arms_mobile.domain.models.Celebration
import com.amalitech.arms_mobile.domain.models.Staff
import kotlinx.datetime.Clock

object CelebrationAdapter : AdapterInterface<CelebrationEntity, Celebration> {
    override fun invoke(entity: CelebrationEntity): Celebration {
        val currentMoment = Clock.System.now()

        return Celebration(
            staff = Staff(
                name = entity.name.toString(),
                firstName = entity.name.toString(),
                lastName = entity.name.toString(),
                id = entity.userId ?: currentMoment.toString()
            ),
            anniversary = entity.anniversaryDate,
            birthday = entity.anniversaryDate
        )
    }
}