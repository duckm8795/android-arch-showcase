package com.arch.list.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arch.common.viewmodel.BaseViewModel
import com.arch.common.viewmodel.NoEvent
import com.arch.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * `UserListViewModel` is a [HiltViewModel] that manages the state and logic for the User List screen.
 * It extends [BaseViewModel] to handle UI state updates and provides a paginated list of users.
 *
 * @param getUsersUseCase A use case that fetches a paginated list of users.
 *
 * Features:
 * - Initializes with a default [UserListUiState] where `isRefreshing` is false.
 * - Provides a [userPaging] flow for paginated user data, which is cached in the [viewModelScope].
 * - Overrides [setLoading] to avoid changing the loading state when the UI is in a refreshing state.
 * - Adds a custom method [setRefreshing] to manage the refreshing state of the UI.
 *
 * Properties:
 * - `userPaging`: A flow of paginated users, cached in the [viewModelScope].
 *
 * Methods:
 * - [setLoading]:
 *   - Overrides the base implementation to check the refreshing state before setting the loading flag.
 *   - Ensures that loading state updates do not conflict with the refreshing state.
 * - [setRefreshing]:
 *   - Updates the [isRefreshing] flag in the UI state.
 *
 * Usage Example:
 * ```kotlin
 * @Composable
 * fun UserListScreen(viewModel: UserListViewModel = hiltViewModel()) {
 *     val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
 *     val pagingData = viewModel.userPaging.collectAsLazyPagingItems()
 *
 *     SwipeRefresh(
 *         state = rememberSwipeRefreshState(uiState.isRefreshing),
 *         onRefresh = { viewModel.setRefreshing(true) }
 *     ) {
 *         LazyColumn {
 *             items(pagingData) { user ->
 *                 UserRow(user)
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * Notes:
 * - The refreshing and loading states are managed separately to ensure better UX during pull-to-refresh actions.
 * - The [userPaging] flow should be collected in a composable context using `collectAsLazyPagingItems`.
 */

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase,
) : BaseViewModel<UserListUiState, NoEvent>(
    initialUiState = UserListUiState(),
) {
    val userPaging = getUsersUseCase()
        .cachedIn(viewModelScope)

    override fun setLoading(isLoading: Boolean) {
        if (!uiState.isRefreshing) {
            super.setLoading(isLoading)
        }
    }

    fun setRefreshing(isRefreshing: Boolean) {
        updateUiState { copy(isRefreshing = isRefreshing) }
    }
}

internal data class UserListUiState(
    val isRefreshing: Boolean = false,
)