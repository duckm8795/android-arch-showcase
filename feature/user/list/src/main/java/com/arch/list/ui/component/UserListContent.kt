package com.arch.list.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arch.common.ui.theme.AppTheme
import com.arch.domain.model.User
import com.arch.userlist.R
import kotlinx.coroutines.flow.flowOf

/**
 * Composable function that renders the main content of the User List screen, including a top app bar,
 * a pull-to-refresh layout, and a paginated user list.
 *
 * @param modifier A [Modifier] to customize the layout or appearance of the content. Defaults to [Modifier].
 * @param pagingItems A [LazyPagingItems] of [User], providing the paginated user data to display.
 * @param isRefreshing A [Boolean] flag indicating whether the pull-to-refresh indicator is active.
 * Defaults to `false`.
 * @param onRefresh A lambda function invoked when the user performs a pull-to-refresh action.
 * Defaults to an empty function.
 * @param onRetryClick A lambda function invoked when the user clicks the retry button in case of a loading error.
 * Defaults to an empty function.
 * @param onUserClick A lambda function invoked when a user is clicked. Receives the username as a [String].
 * Defaults to an empty function.
 * @param onUrlClick A lambda function invoked when a URL is clicked. Receives the URL as a [String].
 * Defaults to an empty function.
 *
 * Features:
 * - Displays a [CenterAlignedTopAppBar] with the title of the screen.
 * - Uses a [PullToRefreshBox] to enable pull-to-refresh functionality.
 * - Displays a [UserList] composable to render the list of users, with support for paging, retries, and interactions.
 * - Handles padding for the content area using the `paddingValues` from [Scaffold].
 *
 * Structure:
 * - Top bar: Displays the title of the screen.
 * - Refresh layout: Wraps the user list in a pull-to-refresh container.
 * - User list: Displays paginated user data with support for click interactions and error handling.
 *
 * Example Usage:
 * ```kotlin
 * @Composable
 * fun ExampleUserListScreen() {
 *     val pagingItems = remember { FakePagingSource().collectAsLazyPagingItems() }
 *     UserListContent(
 *         pagingItems = pagingItems,
 *         isRefreshing = false,
 *         onRefresh = { pagingItems.refresh() },
 *         onRetryClick = { pagingItems.retry() },
 *         onUserClick = { username -> println("User clicked: $username") },
 *         onUrlClick = { url -> println("URL clicked: $url") }
 *     )
 * }
 * ```
 *
 * Notes:
 * - The [UserList] composable is responsible for rendering the actual list of users.
 * - Ensure the [pagingItems] flow is properly managed in the parent composable to avoid data inconsistencies.
 * - The default behavior for click handlers is to do nothing; customize these lambdas as needed.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserListContent(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<User>,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onUserClick: (String) -> Unit = {},
    onUrlClick: (String) -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.user_list_title)) },
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier.padding(paddingValues)
        ) {
            UserList(
                pagingItems = pagingItems,
                modifier = Modifier.fillMaxSize(),
                onRetryClick = onRetryClick,
                onUserClick = onUserClick,
                onUrlClick = onUrlClick,
            )
        }
    }
}

@Preview
@Composable
private fun UserListContentPreview() {
    AppTheme {
        UserListContent(
            pagingItems = flowOf(PagingData.empty<User>()).collectAsLazyPagingItems(),
        )
    }
}