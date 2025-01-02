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
