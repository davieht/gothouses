package com.daveloper.gothouses.presentation.detailscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.gothouses.data.Resource
import com.daveloper.gothouses.domain.model.House
import com.daveloper.gothouses.domain.usecases.UseCases
import com.daveloper.gothouses.presentation.MutableResourceState
import com.daveloper.gothouses.presentation.ResourceState
import com.daveloper.gothouses.presentation._ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _state: MutableResourceState<House> = mutableStateOf(_ResourceState(House(-1)))
    val state: ResourceState<House> = _state

    private var houseJob: Job? = null

    init {
        val id = savedStateHandle.get<Int>("id")

        if (id != null && id != -1) {
            loadHouse(id)
        }
    }

    fun loadHouse(id: Int) {
        houseJob?.cancel()
        houseJob = useCases.getHouse(id).onEach {
            _state.value = state.value.copy(
                isLoading = it is Resource.Loading,
                isError = it is Resource.Error,
                item = if (it is Resource.Success) it.data else null
            )
        }.launchIn(viewModelScope)
    }
}