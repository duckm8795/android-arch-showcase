package com.arch.detail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arch.common.ui.theme.AppTheme
import com.arch.detail.ui.UserDetailUiState
import com.arch.domain.model.User
import com.arch.domain.ui.UserItem
import com.arch.userdetail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserDetailsContent(
    userDetails: UserDetailUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onBlogClick: (String) -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.user_details_title)) },
                navigationIcon = { BackButton(onClick = onBackClick) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserItem(
                user = User(
                    username = userDetails.username,
                    avatarUrl = userDetails.avatarUrl,
                    location = userDetails.location,
                ),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                UserStat(
                    drawableResId = R.drawable.ic_followers,
                    label = stringResource(R.string.user_details_followers),
                    value = userDetails.followers,
                )
                UserStat(
                    drawableResId = R.drawable.ic_following,
                    label = stringResource(R.string.user_details_following),
                    value = userDetails.following,
                )
            }
            UserBlog(
                url = userDetails.url,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                onClick = onBlogClick,
            )
        }
    }
}

@Preview
@Composable
private fun UserDetailsContentPreview() {
    AppTheme {
        UserDetailsContent(
            userDetails = UserDetailUiState(
                username = "user1",
                avatarUrl = "https://avatars.githubusercontent.com/u/123456",
                location = "Vietnam",
                followers = "100+",
                following = "200+",
                url = "https://tyme.com",
            ),
        )
    }
}