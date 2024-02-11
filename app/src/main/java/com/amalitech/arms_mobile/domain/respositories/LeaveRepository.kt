package com.amalitech.arms_mobile.domain.respositories

import com.amalitech.arms_mobile.domain.entities.StaffEntity

interface LeaveRepository {
    suspend fun whoIsOut() : List<StaffEntity>?
}