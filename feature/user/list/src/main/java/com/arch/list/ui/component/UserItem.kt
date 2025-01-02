package com.arch.list.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arch.common.ui.theme.AppTheme
import com.arch.domain.model.User
import com.arch.domain.ui.UrlText
import com.arch.domain.ui.UserAvatar

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onUserClick: (String) -> Unit = {},
    onUrlClick: (String) -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .clickable { onUserClick(user.username) }
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            UserAvatar(
                avatarUrl = user.avatarUrl,
                modifier = Modifier.size(90.dp),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.titleMedium,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                )
                UrlText(
                    url = user.url,
                    onClick = onUrlClick,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
private fun UserCardPreview() {
    AppTheme {
        UserItem(
            user = User(
                id = 1,
                username = "JohnDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://github.com/joindoe",
            ),
        )
    }
}