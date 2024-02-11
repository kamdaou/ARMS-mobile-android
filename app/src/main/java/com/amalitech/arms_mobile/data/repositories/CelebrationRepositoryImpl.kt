package com.amalitech.arms_mobile.data.repositories

import com.amalitech.arms_mobile.domain.entities.CelebrationEntity
import com.amalitech.arms_mobile.domain.respositories.CelebrationRepository

class CelebrationRepositoryImpl: CelebrationRepository {
    override suspend fun all(fetchAll: Boolean): CelebrationEntity {
        TODO("Get all Celebrations based on the fetchAll parameter")
    }
}