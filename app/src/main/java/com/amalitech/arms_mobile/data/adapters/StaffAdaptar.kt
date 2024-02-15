package com.amalitech.arms_mobile.data.adapters

import com.amalitech.arms_mobile.core.utilities.AdapterInterface
import com.amalitech.arms_mobile.data.entities.StaffEntity
import com.amalitech.arms_mobile.domain.models.Staff

object StaffAdapter : AdapterInterface<StaffEntity, Staff> {
    override operator fun invoke(entity: StaffEntity): Staff {
        return Staff(name = entity.name)
    }
}