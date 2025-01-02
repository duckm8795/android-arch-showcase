package com.arch.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.arch.common.extension.openBrowser
import com.arch.common.ui.BaseUiStateScreen
import com.arch.detail.ui.component.UserDetailsContent
import com.arch.detail.viewmodel.UserDetailViewModel

/**
 * Composable function that renders the User Detail screen, integrating UI state and user interactions.
 *
 * @param viewModel The [UserDetailViewModel] responsible for managing the UI state and user details.
 * Defaults to an instance provided by [hiltViewModel].
 * @param onBackClick A callback function triggered when the back button is clicked. Defaults to an empty function.
 *
 * Features:
 * - Integrates with the [BaseUiStateScreen] to observe and react to UI state changes.
 * - Delegates the actual UI rendering to [UserDetailsContent].
 * - Handles external URL navigation using the [LocalContext.openBrowser] extension function.
 *
 * Parameters Observed:
 * - The UI state provided by the [viewModel.uiStateFlow], which contains the user details.
 *
 * Example Usage:
 * ```kotlin
 * @Composable
 * fun AppNavigation() {
 *     val navController = rememberNavController()
 *     NavHost(navController = navController, startDestination = UserListRoute) {
 *         composable("userDetail") {
 *             UserDetailScreen(
 *                 onBackClick = { navController.navigateUp() }
 *             )
 *         }
 *     }
 * }
 * ```
 *
 * Notes:
 * - The [onBackClick] callback should handle navigation back to the previous screen.
 * - The [onBlogClick] action uses [LocalContext.openBrowser] to open external URLs in the browser.
 * - Ensure the `viewModel` is scoped correctly to the navigation graph to maintain state during configuration changes.
 */

@Composable
internal fun UserDetailScreen(
    viewModel: UserDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
) {
    val context = LocalContext.current

    BaseUiStateScreen(
        viewModel = viewModel,
    ) { uiState ->
        UserDetailsContent(
            userDetails = uiState,
            onBackClick = onBackClick,
            onBlogClick = { url -> context.openBrowser(url) },
        )
    }
}