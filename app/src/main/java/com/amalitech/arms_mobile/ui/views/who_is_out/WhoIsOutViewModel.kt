package com.amalitech.arms_mobile.ui.views.who_is_out

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.core.utilities.TypedResponse
import com.amalitech.arms_mobile.domain.models.Staff
import com.amalitech.arms_mobile.domain.usecases.GetWhoIsOutUseCase
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
    val checkedList: List<String> = listOf("All", "Today", "Tomorrow"),
    val excludeTomorrow: Boolean = false,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val message: String = "",
)

@HiltViewModel
class WhoIsOutViewModel @Inject constructor(
    private val getWhoIsOutUseCase: GetWhoIsOutUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WhoIsOutUiState())
    val state: StateFlow<WhoIsOutUiState> = _state.asStateFlow()

    private var requestHandler: Job? = null

    var data: List<Staff> = listOf()
        get() {
            var data: List<Staff> = state.value.today

            if (!state.value.excludeTomorrow) {
                data = data + state.value.tomorrow
            }

            return data
        }
        private set

    private suspend fun getWhoIsOutData() {
        _state.update {
            it.copy(isLoading = true)
        }
        when (val response = getWhoIsOutUseCase()) {
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

    fun onChecked(label: List<String>) {
        _state.update {
            it.copy(
                checkedList = label
            )
        }
    }

    init {
        viewModelScope.launch {
            getWhoIsOutData()
        }
    }
}