package com.amalitech.arms_mobile.ui.views.leaves

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.usecases.GetLeavesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhoIsOutViewModel @Inject constructor(
    private val getLeavesUseCase: GetLeavesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WhoIsOutUiState())
    val state: StateFlow<WhoIsOutUiState> = _state.asStateFlow()

    private val _filteredData: MutableStateFlow<List<Staff>> = MutableStateFlow(emptyList())
    val filteredData: StateFlow<List<Staff>> = _filteredData.asStateFlow()

    init {
        getWhoIsOutData()
    }

    private fun getWhoIsOutData() {
        _state.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val response = getLeavesUseCase()) {
                is TypedResponse.Success -> {
                    val data = response.data

                    Log.d("LOG :> ", data.toString())

                    _state.update {
                        it.copy(
                            today = data?.first ?: listOf(),
                            tomorrow = data?.second ?: listOf(),
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
                checkedList = label
            )
        }
        updateFilteredData()
    }

    private fun updateFilteredData() {
        val labels = state.value.checkedList
        val today = state.value.today
        val tomorrow = state.value.tomorrow

        if (labels.containsAll(listOf("Today", "Tomorrow"))) {
            if (state.value.excludeTomorrow) {
                _filteredData.update {
                    today
                }
                return
            }
            _filteredData.update {
                (today + tomorrow).toSet().toList()
            }
            return
        }

        if (labels.contains("Today")) {
            _filteredData.update {
                today
            }
            return
        }

        if (labels.contains("Tomorrow")) {
            _filteredData.update {
                tomorrow
            }
            return
        }

        _filteredData.update {
            emptyList()
        }
    }
}
