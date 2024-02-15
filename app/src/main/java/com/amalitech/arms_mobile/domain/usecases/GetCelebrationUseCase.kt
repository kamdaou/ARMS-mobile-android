package com.amalitech.arms_mobile.domain.usecases

import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.core.utilities.UseCase
import com.amalitech.arms_mobile.domain.models.Celebration
import com.amalitech.arms_mobile.domain.respositories.CelebrationRepository
import javax.inject.Inject

class GetCelebrationUseCase @Inject constructor(
    private val repository: CelebrationRepository
) : UseCase<Unit?, TypedResponse<List<Celebration>>> {

    override suspend fun invoke(args: Unit?): TypedResponse<List<Celebration>> {
        return repository.all()
    }
}