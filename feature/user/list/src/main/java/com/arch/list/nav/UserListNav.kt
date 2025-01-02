package com.arch.list.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arch.list.ui.UserListScreen
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute

fun NavGraphBuilder.userListScreen() {
    composable<UserListRoute> {
        UserListScreen()
    }
}