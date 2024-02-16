package com.amalitech.arms_mobile.ui.views.celebrations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.domain.models.Celebration
import com.amalitech.arms_mobile.domain.usecases.GetCelebrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class CelebrationUiState(
    val celebrations: List<Celebration> = listOf(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)

@HiltViewModel
class CelebrationViewModel @Inject constructor(
    private val getCelebrationUseCase: GetCelebrationUseCase,
) : ViewModel() {

    private val _celebrationState = MutableStateFlow(CelebrationUiState())
    val celebrationState: StateFlow<CelebrationUiState> = _celebrationState.asStateFlow()

    private var requestHandler: Job? = null

    private suspend fun getCelebrationData() {
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
            getCelebrationData()
        }
    }
}