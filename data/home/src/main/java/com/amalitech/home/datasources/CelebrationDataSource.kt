package com.amalitech.home.datasources

import com.amalitech.GetCelebrationsQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import javax.inject.Inject

class CelebrationDataSource @Inject constructor(
    private val apolloClient: ApolloClient,
) {

    suspend fun celebrations(): ApolloResponse<GetCelebrationsQuery.Data> {
        return apolloClient
            .query(GetCelebrationsQuery())
            .execute()
    }
}

fun GetCelebrationsQuery.GetCelebration.filterNullProperties(): Boolean {
    val properties = full_name != null && position_name != null
    return properties
}