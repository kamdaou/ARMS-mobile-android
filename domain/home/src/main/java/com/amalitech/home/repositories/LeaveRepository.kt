package com.amalitech.home.repositories

import com.amalitech.domain.utils.TypedResponse
import com.amalitech.home.models.Staff
import com.amalitech.home.utils.WhoIsOutResponse

interface LeaveRepository {
    suspend fun whoIsOut(): TypedResponse<WhoIsOutResponse<Staff>>

}