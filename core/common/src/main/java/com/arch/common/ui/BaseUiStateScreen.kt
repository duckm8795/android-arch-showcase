package com.arch.common.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.common.ui.component.Loading
import com.arch.common.viewmodel.BaseViewModel

/**
 * A generic composable function that serves as a base UI screen for managing and displaying UI state
 * and loading indicators. It integrates with a [BaseViewModel] to observe state changes and react accordingly.
 *
 * @param UiState The type representing the UI state managed by the ViewModel.
 * @param Event The type representing events that the ViewModel can handle.
 * @param viewModel An instance of [BaseViewModel] that provides the UI state and loading status.
 * @param content A composable lambda to render the UI based on the current [UiState].
 *
 * This function observes:
 * - [uiState] from the ViewModel's [uiStateFlow] to display the relevant UI.
 * - [isLoading] from the ViewModel's [isLoading] flow to show or hide a loading indicator.
 *
 * Structure:
 * - Uses a [Box] to overlay the loading indicator on top of the main content.
 * - The [content] lambda is invoked with the current [UiState] to render the main UI.
 * - Displays a loading indicator using [AnimatedVisibility] with fade-in and fade-out animations.
 *
 * Example Usage:
 * ```kotlin
 * @Composable
 * fun ExampleScreen(viewModel: ExampleViewModel) {
 *     BaseUiStateScreen(viewModel = viewModel) { uiState ->
 *         // Render your UI here based on the provided uiState
 *         Text(text = uiState.exampleText)
 *     }
 * }
 *
 * data class ExampleUiState(val exampleText: String = "Hello World")
 * ```
 */
@Composable
fun <UiState, Event> BaseUiStateScreen(
    viewModel: BaseViewModel<UiState, Event>,
    content: @Composable (uiState: UiState) -> Unit,
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        content(uiState)
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Loading()
        }
    }
}