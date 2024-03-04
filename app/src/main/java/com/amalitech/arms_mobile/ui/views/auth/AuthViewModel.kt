package com.amalitech.arms_mobile.ui.views.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.data.data_source.TokenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStore: TokenDataStore,
) : ViewModel() {

    fun storeAccessToken(token: String) {
        viewModelScope.launch {
            dataStore.storeAccessToken(token)
        }
    }

    fun loggedInState(loggedIn: Boolean) {
        viewModelScope.launch {
            dataStore.storeLoginState(loggedIn)
        }
    }

    fun storeUserData(name: String? = null, photo: String? = null) {
        viewModelScope.launch {
            if (name != null || photo != null) {
                if (name != null) {
                    launch { dataStore.storeUserData(name) }
                }
                if (photo != null) {
                    launch { dataStore.storeUserPhoto(photo) }
                }
            }
        }
    }
}