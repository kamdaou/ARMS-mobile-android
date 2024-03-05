package com.amalitech.home.use_cases

import com.amalitech.domain.utils.TypedResponse
import com.amalitech.home.models.Celebration
import com.amalitech.home.repositories.CelebrationRepository
import javax.inject.Inject

class GetCelebrationUseCase @Inject constructor(
    private val repository: CelebrationRepository
) {

    suspend operator fun invoke(): TypedResponse<List<Celebration>> {
        return repository.all()
    }
}