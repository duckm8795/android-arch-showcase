package com.arch.list.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.arch.common.ui.theme.AppTheme
import com.arch.domain.model.User
import com.arch.domain.ui.UserItem
import com.arch.userlist.R
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun UserList(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<User>,
    onRetryClick: () -> Unit = {},
    onUserClick: (String) -> Unit = {},
    onUrlClick: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { user -> user.id },
        ) { index ->
            val user = pagingItems[index] ?: return@items
            UserItem(
                user = user,
                onUserClick = onUserClick,
                onUrlClick = onUrlClick,
            )
        }

        when (pagingItems.loadState.append) {
            is LoadState.Error -> item {
                Button(
                    onClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.user_list_retry_text))
                }
            }

            is LoadState.Loading -> item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                )
            }

            else -> {}
        }
    }
}

@Preview
@Composable
private fun UserListPreview() {
    AppTheme {
        val userList = MutableList(10) { index ->
            User(
                id = index,
                username = "user$index",
                url = "https://www.github.com/user$index",
            )
        }.toImmutableList()
        val userPagingItems = flowOf(PagingData.from(userList)).collectAsLazyPagingItems()
        UserList(pagingItems = userPagingItems)
    }
}