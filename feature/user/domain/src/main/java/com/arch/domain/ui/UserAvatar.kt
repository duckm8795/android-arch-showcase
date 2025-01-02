package com.arch.domain.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arch.common.ui.theme.AppTheme
import com.arch.domain.R

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    avatarUrl: String,
) {
    val colors = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.tertiary,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    )
    Card(
        modifier = modifier,
        colors = colors,
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_user_placeholder),
            placeholder = painterResource(R.drawable.ic_user_placeholder),
        )
    }
}

@Preview
@Composable
private fun UserAvatarPreview() {
    AppTheme {
        UserAvatar(
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            modifier = Modifier.size(120.dp),
        )
    }
}