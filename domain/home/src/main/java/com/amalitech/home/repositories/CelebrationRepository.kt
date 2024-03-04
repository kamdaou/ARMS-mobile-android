package com.amalitech.home.repositories

import com.amalitech.domain.utils.TypedResponse
import com.amalitech.home.models.Celebration

interface CelebrationRepository {
    suspend fun all(fetchAll: Boolean = false): TypedResponse<List<Celebration>>

}