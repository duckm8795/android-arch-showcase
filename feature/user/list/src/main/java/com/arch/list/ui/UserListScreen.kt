package com.arch.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.arch.common.extension.openBrowser
import com.arch.common.ui.BaseUiStateScreen
import com.arch.list.ui.component.UserListContent
import com.arch.list.viewmodel.UserListViewModel
/**
 * Composable function that renders the User List screen, integrating UI state, paging, and refresh logic.
 *
 * @param viewModel The [UserListViewModel] responsible for managing the screen's UI state and user list data.
 * Defaults to an instance provided by [hiltViewModel].
 * @param onUserClick A callback function triggered when a user is clicked. Receives the username as a [String].
 *
 * Features:
 * - Integrates with a [LazyPagingItems] flow to display a paginated list of users.
 * - Handles loading and error states from the paging source.
 * - Supports pull-to-refresh functionality using [isRefreshing] and [onRefresh].
 * - Allows users to retry failed loading operations and open external URLs.
 *
 * Structure:
 * - Uses [BaseUiStateScreen] to handle loading indicators and manage UI state updates.
 * - Observes the [LoadState] of [pagingItems] to trigger loading and error handling.
 * - Delegates the actual UI rendering to [UserListContent].
 *
 * Parameters Observed:
 * - `pagingItems.loadState.refresh`: Monitored using [LaunchedEffect] to update loading and refreshing states.
 * - `uiState.isRefreshing`: Determines whether the pull-to-refresh indicator is shown.
 *
 * Example Usage:
 * ```kotlin
 * @Composable
 * fun AppNavigation() {
 *     val navController = rememberNavController()
 *     NavHost(navController = navController, startDestination = UserListRoute) {
 *         userListScreen { username ->
 *             navController.navigate("userDetail/$username")
 *         }
 *     }
 * }
 * ```
 *
 * Notes:
 * - The `onUrlClick` action uses [Context.openBrowser] to open external URLs.
 * - Error handling in the `refresh` state is currently a placeholder and should be implemented.
 */
@Composable
internal fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onUserClick: (String) -> Unit,
) {
    val pagingItems = viewModel.userPaging.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(pagingItems.loadState.refresh) {
        when (pagingItems.loadState.refresh) {
            is LoadState.Loading -> viewModel.setLoading(true)
            is LoadState.Error -> {
                // handle error later
            }
            else -> {
                viewModel.setLoading(false)
                viewModel.setRefreshing(false)
            }
        }
    }

    BaseUiStateScreen(
        viewModel = viewModel,
    ) { uiState ->
        UserListContent(
            isRefreshing = uiState.isRefreshing,
            onRefresh = {
                pagingItems.refresh()
                viewModel.setRefreshing(true)
            },
            pagingItems = pagingItems,
            onRetryClick = { pagingItems.retry() },
            onUserClick = onUserClick,
            onUrlClick = { url -> context.openBrowser(url) },
        )
    }
}
