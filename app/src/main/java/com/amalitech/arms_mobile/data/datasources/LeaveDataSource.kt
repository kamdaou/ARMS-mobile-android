package com.amalitech.arms_mobile.data.datasources

import android.util.Log
import com.amalitech.WhoIsOutQuery
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.data.entities.StaffEntity
import com.amalitech.arms_mobile.data.entities.toStaffEntity
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class LeaveDataSource @Inject constructor(
    private val apolloClient: ApolloClient,
) {
    suspend fun whoIsOut(): TypedResponse<Triple<List<StaffEntity>, List<StaffEntity>, Boolean>?> {
        val response = apolloClient
            .query(WhoIsOutQuery())
            .execute()

        val errors = response.errors?.first()?.message

        Log.d("APP::LeaveData", errors.toString())

        if (!response.hasErrors()) {
            val data = response.data?.whoIsOut

            val today = data?.today?.mapNotNull { it!!.toStaffEntity() } ?: emptyList()
            val tomorrow = data?.tomorrow?.mapNotNull { it!!.toStaffEntity() } ?: emptyList()

            return TypedResponse.Success(
                data = Triple(today, tomorrow,data?.is_friday ?: false)
            )
        }

        return TypedResponse.Error(
            message = errors ?: "Couldn't retrieve data",
        )
    }

}