package com.amalitech.data.di

import com.amalitech.data.BuildConfig
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        val token = ""

        return ApolloClient.Builder()
            .serverUrl(BuildConfig.GRAPHQL_URL)
            .addHttpHeader("Authorization", token)
            .build()
    }
}