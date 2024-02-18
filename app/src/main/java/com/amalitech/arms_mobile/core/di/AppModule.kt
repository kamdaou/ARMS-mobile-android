package com.amalitech.arms_mobile.core.di

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.amalitech.arms_mobile.BuildConfig
import com.amalitech.arms_mobile.TokenDataStore
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val APP_DATA_NAME = "app_data"

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

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) : TokenDataStore {
        val dataStore =  PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(context, APP_DATA_NAME)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(APP_DATA_NAME) }
        )

        return TokenDataStore(dataStore)
    }
}