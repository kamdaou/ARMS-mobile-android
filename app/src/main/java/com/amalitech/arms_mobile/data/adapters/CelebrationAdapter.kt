package com.amalitech.arms_mobile.data.adapters

import com.amalitech.arms_mobile.core.utilities.AdapterInterface
import com.amalitech.arms_mobile.data.entities.CelebrationEntity
import com.amalitech.arms_mobile.domain.models.Celebration
import com.amalitech.arms_mobile.domain.models.Staff

object CelebrationAdapter : AdapterInterface<CelebrationEntity, Celebration> {
    override fun invoke(entity: CelebrationEntity): Celebration {
        return Celebration(
            staff = Staff(
                name = entity.name,
                firstName = entity.name,
                lastName = entity.name,
                id = entity.userId
            ),
            anniversary = entity.anniversaryDate,
            birthday = entity.anniversaryDate
        )
    }
}