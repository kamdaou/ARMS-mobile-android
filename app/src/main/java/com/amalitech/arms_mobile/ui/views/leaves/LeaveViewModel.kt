package com.amalitech.arms_mobile.ui.views.leaves

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.usecases.GetLeavesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class WhoIsOutUiState(
    val today: List<Staff> = listOf(),
    val tomorrow: List<Staff> = listOf(),
    val checkedList: List<String> = listOf("Today", "Tomorrow"),
    val excludeTomorrow: Boolean = false,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)

@HiltViewModel
class WhoIsOutViewModel @Inject constructor(
    private val getLeavesUseCase: GetLeavesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WhoIsOutUiState())
    val state: StateFlow<WhoIsOutUiState> = _state.asStateFlow()

    private var requestHandler: Job? = null

    val filteredData: List<Staff>
        get() {
            val labels = state.value.checkedList
            val today = state.value.today
            val tomorrow = state.value.tomorrow

            if (labels.containsAll(listOf("Today", "Tomorrow"))) {
                if (state.value.excludeTomorrow) {
                    return today
                }
                return (today + tomorrow).toSet().toList()
            }

            if (labels.contains("Today")) {
                return today
            }

            if (labels.contains("Tomorrow")) {
                return tomorrow
            }

            return emptyList()
        }

    init {
        viewModelScope.launch {
            getWhoIsOutData()
        }
    }

    private suspend fun getWhoIsOutData() {
        _state.update {
            it.copy(isLoading = true)
        }
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
                checkedList = label
            )
        }
    }

}