package com.arch.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.arch.common.extension.openBrowser
import com.arch.common.ui.BaseUiStateScreen
import com.arch.detail.ui.component.UserDetailsContent
import com.arch.detail.viewmodel.UserDetailsViewModel

@Composable
internal fun UserDetailScreen(
    viewModel: UserDetailsViewModel = hiltViewModel(),
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