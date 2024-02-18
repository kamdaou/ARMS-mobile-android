package com.amalitech.arms_mobile.domain.respositories

import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.core.utilities.WhoIsOutResponse
import com.amalitech.arms_mobile.domain.models.Staff

interface LeaveRepository {
    suspend fun whoIsOut() : TypedResponse<WhoIsOutResponse<Staff>>

}