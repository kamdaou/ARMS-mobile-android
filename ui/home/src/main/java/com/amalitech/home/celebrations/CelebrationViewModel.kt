package com.amalitech.home.celebrations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.domain.utils.TypedResponse
import com.amalitech.home.models.Celebration
import com.amalitech.home.use_cases.GetCelebrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebrationViewModel @Inject constructor(
    private val getCelebrationUseCase: GetCelebrationUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CelebrationUiState())
    val state: StateFlow<CelebrationUiState> = _state.asStateFlow()

    private val _filteredData: MutableStateFlow<List<Celebration>> = MutableStateFlow(emptyList())
    val filteredData: StateFlow<List<Celebration>> = _filteredData.asStateFlow()


    init {
        getCelebrationData()
    }

    private fun getCelebrationData() {
        _state.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val response = getCelebrationUseCase()) {
                is TypedResponse.Success -> {
                    val data = response.data
                    val celebrations = data ?: listOf()

                    _state.update {
                        it.copy(
                            celebrations = celebrations,
                        )
                    }
                    updateFilteredData()
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
    }

    fun updateCheckedList(label: List<String>) {
        _state.update {
            it.copy(
                checkedList = label,
            )
        }
        updateFilteredData()
    }

    private fun updateFilteredData() {
        val filtered = state.value.celebrations.filter { data ->
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
        _filteredData.update {
            filtered
        }
    }
}
