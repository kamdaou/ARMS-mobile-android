package com.amalitech.arms_mobile.data.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val ACCESS_TOKEN_KEY = "access_token"
private const val USER_KEY = "user"
private const val USER_DATA = "user_data"

class TokenDataStore(
    private val dataStore: DataStore<Preferences>
) {
    private val accessTokenFlow = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(ACCESS_TOKEN_KEY)] ?: ""
    }
    private val accessPhoto = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_KEY)]
    }

    private val accessUserData = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_DATA)] ?: ""
    }



    suspend fun storeAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(ACCESS_TOKEN_KEY)] = accessToken
        }
    }

    suspend fun storeUserPhoto(photo: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_KEY)] = photo
        }
    }

    suspend fun storeUserData(name: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_DATA)] = name
        }
    }

    fun getUserData() : Flow<String> {
        return accessUserData
    }

    suspend fun clearAccessToken() {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(ACCESS_TOKEN_KEY))
        }
    }

    suspend fun getAccessToken(): String {
        return accessTokenFlow.first()
    }

     fun getUserPhoto(): Flow<String?> {
        return accessPhoto
    }
}
