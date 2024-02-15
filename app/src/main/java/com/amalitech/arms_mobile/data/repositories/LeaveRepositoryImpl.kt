package com.amalitech.arms_mobile.data.repositories

import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.core.utilities.WhoIsOutResponse
import com.amalitech.arms_mobile.data.adapters.StaffAdapter
import com.amalitech.arms_mobile.data.datasources.LeaveDataSource
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.respositories.LeaveRepository
import javax.inject.Inject

class LeaveRepositoryImpl @Inject constructor(
    private val dataSource: LeaveDataSource,
) : LeaveRepository {

    override suspend fun whoIsOut(): TypedResponse<WhoIsOutResponse<Staff>> {
        return when (val response = dataSource.whoIsOut()) {
            is TypedResponse.Success -> response.data.let {
                TypedResponse.Success(
                    data = Triple(
                        it!!.first.map { e -> StaffAdapter(e) },
                        it.second.map { e -> StaffAdapter(e) },
                        it.third
                    )
                )
            }
            else -> TypedResponse.Error(message = response.message ?: "")
        }
    }
}