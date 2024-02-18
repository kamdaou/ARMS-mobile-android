package com.amalitech.arms_mobile.domain.usecases

import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.core.utilities.UseCase
import com.amalitech.arms_mobile.core.utilities.WhoIsOutResponse
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.respositories.LeaveRepository
import javax.inject.Inject

class GetLeavesUseCase @Inject constructor(
    private val repository: LeaveRepository
) : UseCase<Unit, TypedResponse<WhoIsOutResponse<Staff>>> {

    override suspend fun invoke(args: Unit?): TypedResponse<WhoIsOutResponse<Staff>> {
        return repository.whoIsOut()
    }

}