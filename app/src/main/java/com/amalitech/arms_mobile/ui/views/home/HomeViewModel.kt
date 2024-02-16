package com.amalitech.arms_mobile.ui.views.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.usecases.GetCelebrationUseCase
import com.amalitech.arms_mobile.domain.usecases.GetWhoIsOutUseCase
import com.amalitech.arms_mobile.ui.views.celebrations.CelebrationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
//    val whoIsOut: List<Staff> = listOf(),
//    val celebrations: List<Celebration> = listOf(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)

data class LeaveUiState(
    val leaves: List<Staff> = listOf(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWhoIsOutUseCase: GetWhoIsOutUseCase,
    private val getCelebrationUseCase: GetCelebrationUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    private val _leaveState = MutableStateFlow(LeaveUiState())
    private val _celebrationState = MutableStateFlow(CelebrationUiState())

    val state: StateFlow<HomeUiState> = _state.asStateFlow()
    val leaveState: StateFlow<LeaveUiState> = _leaveState.asStateFlow()
    val celebrationState: StateFlow<CelebrationUiState> = _celebrationState.asStateFlow()

    private var requestHandler: Job? = null

    suspend fun getLeaveData() {
        _leaveState.update {
            it.copy(isLoading = true)
        }
        when (val response = getWhoIsOutUseCase()) {
            is TypedResponse.Success -> {
                val data = response.data

                Log.d("LOG :> ", data.toString())

                val today = data?.first ?: listOf()
                val tomorrow = data?.second ?: listOf()

                var whoIsOut = today
                if (data?.third != true) {
                    whoIsOut = today + tomorrow
                }

                _leaveState.update {
                    it.copy(
                        leaves = whoIsOut,
                    )
                }

            }

            is TypedResponse.Error -> {
                _leaveState.update {
                    it.copy(
                        hasError = true
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

    suspend fun getCelebrationData() {
        _celebrationState.update {
            it.copy(isLoading = true)
        }
        when (val response = getCelebrationUseCase()) {
            is TypedResponse.Success -> {
                val data = response.data

                Log.d("LOG :> ", data.toString())

                val celebrations = data ?: listOf()

                _celebrationState.update {
                    it.copy(
                        celebrations = celebrations,
                    )
                }

            }

            is TypedResponse.Error -> {
                _celebrationState.update {
                    it.copy(
                        hasError = true
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

    init {
        viewModelScope.launch {
            getLeaveData()
        }

        viewModelScope.launch {
            getCelebrationData()
        }
    }
}