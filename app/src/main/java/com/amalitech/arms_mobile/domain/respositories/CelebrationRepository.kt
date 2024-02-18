package com.amalitech.arms_mobile.domain.respositories

import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.domain.models.Celebration

interface CelebrationRepository {
    suspend fun all(fetchAll: Boolean = false): TypedResponse<List<Celebration>>

}