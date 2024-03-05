package com.amalitech.home.use_cases

import com.amalitech.domain.utils.TypedResponse
import com.amalitech.home.models.Staff
import com.amalitech.home.repositories.LeaveRepository
import com.amalitech.home.utils.WhoIsOutResponse
import javax.inject.Inject

class GetLeavesUseCase @Inject constructor(
    private val repository: LeaveRepository
) {

    suspend operator fun invoke(): TypedResponse<WhoIsOutResponse<Staff>> {
        return repository.whoIsOut()
    }

}