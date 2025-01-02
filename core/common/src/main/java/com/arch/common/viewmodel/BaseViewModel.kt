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

/**
 * BaseViewModel is an abstract class that provides a foundation for managing UI state and events
 * in a reactive manner using Kotlin's Flow and Jetpack ViewModel.
 *
 * @param UiState The type representing the UI state managed by the ViewModel.
 * @param Event The type representing events that the ViewModel can handle.
 * @param initialUiState The initial value for the UI state.
 */
abstract class BaseViewModel<UiState, Event>(initialUiState: UiState) : ViewModel() {
    /**
     * @property uiStateFlow A [StateFlow] that emits the current UI state, allowing observers to
     * react to state changes.
     *
     * @property isLoading A [StateFlow] indicating whether a loading state is active.
     *
     * @property uiState The current value of the UI state, allowing for synchronous access.
     */
    private val _uiState = MutableStateFlow(initialUiState)
    val uiStateFlow: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading.asStateFlow()

    val uiState: UiState
        get() = _uiState.value

    /**
     * Updates the UI state using a provided transformation function.
     * @param update A lambda function to update the current state.
     */
    open fun updateUiState(update: UiState.() -> UiState) {
        _uiState.update { it.update() }
    }

    /**
     * Sets the loading state.
     * @param isLoading A Boolean indicating whether the loading state should be active.
     */
    open fun setLoading(isLoading: Boolean) {
        _isLoading.update { isLoading }
    }

    /**
     * Collects a [Flow] safely with built-in error handling and loading state management.
     *
     * @param T The type of data emitted by the flow.
     * @param context The [CoroutineContext] for the flow operations. Defaults to [EmptyCoroutineContext].
     * @param hasLoading A Boolean indicating whether to manage loading state during collection.
     * @param onError A lambda to handle errors emitted during collection.
     * @param block A suspend lambda to handle each item emitted by the flow.
     *
     * @return A [Job] representing the ongoing collection process.
     */
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

/**
 * A placeholder object representing the absence of an event. Useful for ViewModels that do not
 * handle specific events.
 */
data object NoEvent

/**
 * A placeholder object representing the absence of a UI state. Useful for ViewModels that do not
 * manage a specific UI state.
 */
data object NoUiState
