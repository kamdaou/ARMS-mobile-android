package com.amalitech.arms_mobile.data.repositories

import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.core.utilities.UiText
import com.amalitech.arms_mobile.core.utilities.WhoIsOutResponse
import com.amalitech.arms_mobile.data.adapters.StaffAdapter
import com.amalitech.arms_mobile.data.datasources.LeaveDataSource
import com.amalitech.arms_mobile.data.entities.toStaffEntity
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.respositories.LeaveRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LeaveRepositoryImpl @Inject constructor(
    private val dataSource: LeaveDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LeaveRepository {

    override suspend fun whoIsOut(): TypedResponse<WhoIsOutResponse<Staff>> =
        withContext(ioDispatcher) {
            try {
                val response = dataSource.whoIsOut()
                if (!response.hasErrors()) {
                    val data = response.data?.whoIsOut

                    val today =
                        data?.today?.filter { it != null && it.user?.employee_info?.employee_bio?.full_name != null }
                            ?.mapNotNull { StaffAdapter(it!!.toStaffEntity()) }
                            ?: emptyList()
                    val tomorrow =
                        data?.tomorrow?.filter { it != null && it.user?.employee_info?.employee_bio?.full_name != null }
                            ?.mapNotNull { StaffAdapter(it!!.toStaffEntity()) }
                            ?: emptyList()
                    return@withContext TypedResponse.Success(
                        data = Triple(today, tomorrow, data?.is_friday ?: false)
                    )
                }
                val errors = response.errors?.first()?.message
                if (errors != null) {
                    return@withContext TypedResponse.Error(message = UiText.DynamicString(errors))
                }
                return@withContext TypedResponse.Error(UiText.StringResource(R.string.an_exception_occurred_please_try_again_later))
            } catch (e: Exception) {
                val localizedMessage = e.localizedMessage
                return@withContext if (localizedMessage != null) TypedResponse.Error(
                    message = UiText.DynamicString(
                        localizedMessage
                    )
                )
                else TypedResponse.Error(message = UiText.StringResource(R.string.an_exception_occurred_please_try_again_later))
            }
        }
}