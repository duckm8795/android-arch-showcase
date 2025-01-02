package com.arch.common.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays a loading indicator centered within a full-screen overlay.
 *
 * @param modifier A [Modifier] to customize the appearance or behavior of the loading overlay.
 * Defaults to an unmodified [Modifier].
 *
 * Features:
 * - Displays a [CircularProgressIndicator] with rounded strokes and a customizable track color.
 * - Covers the entire screen using [fillMaxSize] to ensure the loading indicator is visible.
 * - Prevents user interaction during loading using a disabled [clickable] modifier.
 * - Centers the loading indicator within the overlay using [Alignment.Center].
 *
 * Usage:
 * This component is useful for showing a loading state overlay on top of other UI components.
 *
 * Example:
 * ```kotlin
 * @Composable
 * fun ExampleScreen(isLoading: Boolean) {
 *     Box(modifier = Modifier.fillMaxSize()) {
 *         Text("Content goes here")
 *         if (isLoading) {
 *             Loading()
 *         }
 *     }
 * }
 * ```
 */
@Composable
internal fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(false) {},
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            strokeCap = StrokeCap.Round,
            strokeWidth = 4.dp,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    Loading()
}