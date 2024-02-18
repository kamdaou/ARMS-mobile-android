package com.amalitech.arms_mobile.ui.views.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.data.datasources.TokenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStore: TokenDataStore
): ViewModel() {

    val email: StateFlow<String> = dataStore.getUserData().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    val photo: StateFlow<String?> = dataStore.getUserPhoto().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    suspend fun storeAccessToken(token: String) {
        dataStore.storeAccessToken(token)
    }

    fun storeUserData(name: String? = null, photo: String? = null) {
        if(name != null || photo != null) {
            viewModelScope.launch {
                if(name != null) {
                    dataStore.storeUserData(name)
                }
                if(photo != null) {
                    dataStore.storeUserPhoto(photo)
                }
            }
        }
    }
}