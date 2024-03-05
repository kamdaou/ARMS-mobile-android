package com.amalitech.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.data.data_source.TokenDataStore
import com.amalitech.domain.utils.TypedResponse
import com.amalitech.home.celebrations.CelebrationUiState
import com.amalitech.home.use_cases.GetCelebrationUseCase
import com.amalitech.home.use_cases.GetLeavesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLeavesUseCase: GetLeavesUseCase,
    private val getCelebrationUseCase: GetCelebrationUseCase,
    dataStore: TokenDataStore,
) : ViewModel() {
    private val _leaveState = MutableStateFlow(LeaveUiState())
    private val _celebrationState = MutableStateFlow(CelebrationUiState())
    val leaveState: StateFlow<LeaveUiState> = _leaveState.asStateFlow()
    val celebrationState: StateFlow<CelebrationUiState> = _celebrationState.asStateFlow()
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

    fun getLeaveData() {
        _leaveState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val response = getLeavesUseCase()) {
                is TypedResponse.Success -> {
                    val data = response.data
                    val today = data?.first ?: listOf()
                    val tomorrow = data?.second ?: listOf()
                    var whoIsOut = today

                    if (data?.third != true) {
                        whoIsOut = (today + tomorrow).toSet().toList()
                    }

                    _leaveState.update {
                        it.copy(
                            leaves = whoIsOut,
                        )
                    }
                }

                else -> {
                    _leaveState.update {
                        it.copy(
                            hasError = true
                        )
                    }
                }
            }

            _leaveState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getCelebrationData() {
        _celebrationState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val response = getCelebrationUseCase()) {
                is TypedResponse.Success -> {
                    val data = response.data
                    val celebrations = data ?: listOf()

                    _celebrationState.update {
                        it.copy(
                            celebrations = celebrations,
                        )
                    }
                }

                else -> {
                    _celebrationState.update {
                        it.copy(
                            hasError = true
                        )
                    }
                }
            }
            _celebrationState.update {
                it.copy(isLoading = false)
            }
        }
    }

    init {
        getLeaveData()
        getCelebrationData()
    }
}
