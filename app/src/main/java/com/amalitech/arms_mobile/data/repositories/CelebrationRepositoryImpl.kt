package com.amalitech.arms_mobile.data.repositories

import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.data.adapters.CelebrationAdapter
import com.amalitech.arms_mobile.data.datasources.CelebrationDataSource
import com.amalitech.arms_mobile.domain.models.Celebration
import com.amalitech.arms_mobile.domain.respositories.CelebrationRepository
import javax.inject.Inject

class CelebrationRepositoryImpl @Inject constructor(
    private val celebrationDataSource: CelebrationDataSource
): CelebrationRepository {
    override suspend fun all(fetchAll: Boolean): TypedResponse<List<Celebration>> {
        return when(val response = celebrationDataSource.celebrations()) {
            is TypedResponse.Success -> {
                return TypedResponse.Success(
                    data = response.data?.map { CelebrationAdapter(it) }
                )
            }
            else -> TypedResponse.Error(message = response.message ?: "")
        }
    }
}