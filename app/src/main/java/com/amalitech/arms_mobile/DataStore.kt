package com.amalitech.arms_mobile

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.amalitech.arms_mobile.data.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val ACCESS_TOKEN_KEY = "access_token"
private const val USER_KEY = "user"

class TokenDataStore(
    private val dataStore: DataStore<Preferences>
) {

    private val accessTokenFlow = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(ACCESS_TOKEN_KEY)] ?: ""
    }
    private val accessPhotoFlow = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_KEY)] ?: ""
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

    suspend fun clearAccessToken() {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(ACCESS_TOKEN_KEY))
        }
    }

    suspend fun getAccessToken(): String {
        return accessTokenFlow.first()
    }

    suspend fun getUserPhoto(): String {
        return accessPhotoFlow.first()
    }
}
