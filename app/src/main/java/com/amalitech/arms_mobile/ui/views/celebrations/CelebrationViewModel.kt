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
    val checkedList: List<String> = listOf("Anniversary", "Birthday"),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)

@HiltViewModel
class CelebrationViewModel @Inject constructor(
    private val getCelebrationUseCase: GetCelebrationUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CelebrationUiState())
    val state: StateFlow<CelebrationUiState> = _state.asStateFlow()

    private var requestHandler: Job? = null

    val filteredData: List<Celebration>
        get() = state.value.celebrations.filter { data ->
            val labels = state.value.checkedList

            if (labels.containsAll(listOf("Anniversary", "Birthday"))) {
                data.anniversary != null || data.birthday != null
            } else if (labels.contains("Anniversary")) {
                data.anniversary != null
            } else if (labels.contains("Birthday")) {
                data.birthday != null
            } else {
                false
            }
        }

    init {
        viewModelScope.launch {
            getCelebrationData()
        }
    }

    private suspend fun getCelebrationData() {
        _state.update {
            it.copy(isLoading = true)
        }
        when (val response = getCelebrationUseCase()) {
            is TypedResponse.Success -> {
                val data = response.data

                Log.d("LOG :> ", data.toString())

                val celebrations = data ?: listOf()

                _state.update {
                    it.copy(
                        celebrations = celebrations,
                    )
                }

            }

            is TypedResponse.Error -> {
                _state.update {
                    it.copy(
                        hasError = true
                    )
                }
            }

            else -> {
                _state.update {
                    it.copy(
                        hasError = true
                    )
                }
            }
        }
        _state.update {
            it.copy(isLoading = false)
        }

    }

    fun updateCheckedList(label: List<String>) {
        _state.update {
            it.copy(
                checkedList = label,
            )
        }
    }
}