package com.amalitech.arms_mobile.data.repositories

import com.amalitech.WhoIsOutQuery
import com.amalitech.arms_mobile.domain.entities.StaffEntity
import com.amalitech.arms_mobile.domain.entities.toStaffEntity
import com.amalitech.arms_mobile.domain.respositories.LeaveRepository
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class LeaveRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LeaveRepository {
    override suspend fun whoIsOut(): List<StaffEntity>? {
        val results = apolloClient
            .query(WhoIsOutQuery())
            .execute().data?.whoIsOut

        val today = results?.today?.mapNotNull { it!!.user?.toStaffEntity() } ?: emptyList()
        val tomorrow = results?.tomorrow?.mapNotNull { it!!.user?.toStaffEntity() } ?: emptyList()

        return today + tomorrow
    }
}