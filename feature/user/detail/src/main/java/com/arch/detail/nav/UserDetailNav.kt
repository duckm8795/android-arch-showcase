package com.arch.detail.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arch.detail.ui.UserDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data object UserDetailRoute

fun NavGraphBuilder.userDetailScreen() {
    composable<UserDetailRoute> {
        UserDetailScreen()
    }
}
