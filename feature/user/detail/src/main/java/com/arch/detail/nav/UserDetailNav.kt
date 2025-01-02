package com.arch.detail.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.arch.detail.ui.UserDetailScreen
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDetailRoute(
    val username: String = "",
)

fun NavGraphBuilder.userDetailScreen() {
    composable<UserDetailRoute> {
        UserDetailScreen()
    }
}

fun NavController.navigateToUserDetail(
    username: String = "",
    navOptions: NavOptions? = null,
) {
    navigate(route = UserDetailRoute(username), navOptions)
}
