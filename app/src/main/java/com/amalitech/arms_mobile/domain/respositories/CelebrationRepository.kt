package com.amalitech.arms_mobile.domain.respositories

import com.amalitech.arms_mobile.domain.entities.CelebrationEntity

interface CelebrationRepository {
    suspend fun all(fetchAll: Boolean = false): CelebrationEntity
}