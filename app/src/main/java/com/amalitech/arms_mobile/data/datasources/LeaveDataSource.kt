package com.amalitech.arms_mobile.data.datasources

import com.amalitech.WhoIsOutQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import javax.inject.Inject

class LeaveDataSource @Inject constructor(
    private val apolloClient: ApolloClient,
) {
    suspend fun whoIsOut(): ApolloResponse<WhoIsOutQuery.Data> {
        return apolloClient
            .query(WhoIsOutQuery())
            .execute()
    }
}
