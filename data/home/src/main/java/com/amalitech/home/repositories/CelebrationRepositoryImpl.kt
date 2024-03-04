package com.amalitech.home.repositories

import com.amalitech.domain.R
import com.amalitech.domain.utils.TypedResponse
import com.amalitech.domain.utils.UiText
import com.amalitech.home.adapters.CelebrationAdapter
import com.amalitech.home.datasources.CelebrationDataSource
import com.amalitech.home.entities.toCelebrationEntity
import com.amalitech.home.models.Celebration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CelebrationRepositoryImpl @Inject constructor(
    private val celebrationDataSource: CelebrationDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CelebrationRepository {
    override suspend fun all(fetchAll: Boolean): TypedResponse<List<Celebration>> =
        withContext(ioDispatcher) {
            try {
                val response = celebrationDataSource.celebrations()
                val errors = response.errors?.first()?.message

                if (!response.hasErrors()) {
                    val data = response.data?.getCelebrations

                    val celebrations =
                        data?.mapNotNull { CelebrationAdapter(it!!.toCelebrationEntity()) }
                            ?: emptyList()

                    return@withContext TypedResponse.Success(
                        data = celebrations
                    )
                }

                return@withContext if (errors != null) TypedResponse.Error(
                    message = UiText.DynamicString(errors),
                ) else TypedResponse.Error(UiText.StringResource(R.string.couldn_t_retrieve_data))
            } catch (e: Exception) {
                val localizedMessage = e.localizedMessage
                if (localizedMessage != null)
                    return@withContext TypedResponse.Error(
                        message = UiText.DynamicString(localizedMessage)
                    )
                return@withContext TypedResponse.Error(UiText.StringResource(R.string.an_exception_occurred_please_try_again_later))
            }
        }
}
