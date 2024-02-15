package com.amalitech.arms_mobile.core.di

import com.amalitech.arms_mobile.data.datasources.CelebrationDataSource
import com.amalitech.arms_mobile.data.datasources.LeaveDataSource
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideLeaveDataSource(apolloClient: ApolloClient) : LeaveDataSource {
        return LeaveDataSource(apolloClient)
    }

    @Singleton
    @Provides
    fun provideCelebrationDataSource(apolloClient: ApolloClient) : CelebrationDataSource {
        return CelebrationDataSource(apolloClient)
    }

}