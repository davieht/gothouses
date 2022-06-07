package com.daveloper.gothouses.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State

typealias MutableResourceState<T> = MutableState<_ResourceState<T>>
typealias ResourceState<T> = State<_ResourceState<T>>

data class _ResourceState<T>(
    val item: T? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
