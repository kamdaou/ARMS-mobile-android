package com.amalitech.arms_mobile.data.datasources

import com.amalitech.GetCelebrationsQuery
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.data.entities.CelebrationEntity
import com.amalitech.arms_mobile.data.entities.toCelebrationEntity
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class CelebrationDataSource@Inject constructor(
    private val apolloClient: ApolloClient,
) {

    suspend fun celerations(): TypedResponse<List<CelebrationEntity>> {
        val response = apolloClient
            .query(GetCelebrationsQuery())
            .execute()

        val errors = response.errors?.first()?.message

        if (!response.hasErrors()) {
            val data = response.data?.getCelebrations

            val celebrations = data?.mapNotNull { it!!.toCelebrationEntity() } ?: emptyList()

            return TypedResponse.Success(
                data = celebrations
            )
        }

        return TypedResponse.Error(
            message = errors ?: "Couldn't retrieve data",
        )
    }
}