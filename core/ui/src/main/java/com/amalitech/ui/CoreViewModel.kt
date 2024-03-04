package com.amalitech.ui

import androidx.lifecycle.ViewModel
import com.amalitech.data.data_source.TokenDataStore

class CoreViewModel(
    private val dataStore: TokenDataStore
) : ViewModel()
