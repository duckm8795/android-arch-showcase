package com.arch.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<UiState, Event>(initialUiState: UiState) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiStateFlow: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading.asStateFlow()


    // Accessor for the current UI state value
    val uiState: UiState
        get() = _uiState.value

    open fun updateUiState(update: UiState.() -> UiState) {
        _uiState.update { it.update() }
    }

    open fun setLoading(isLoading: Boolean) {
        _isLoading.update { isLoading }
    }

    fun <T> Flow<T>.collectSafe(
        context: CoroutineContext = EmptyCoroutineContext,
        hasLoading: Boolean = false,
        onError: (Throwable) -> Unit = {},
        block: suspend (T) -> Unit,
    ): Job = flowOn(context)
        .onStart { if (hasLoading) setLoading(true) }
        .catch { e ->
            onError(e)
            if (hasLoading) setLoading(false)
        }
        .onEach {
            block(it)
            if (hasLoading) setLoading(false)
        }
        .launchIn(viewModelScope)
}

data object NoEvent
data object NoUiState
