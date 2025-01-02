package com.arch.list.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arch.list.ui.UserListScreen
import kotlinx.serialization.Serializable

/**
 * `UserListRoute` is a [Serializable] data object representing the navigation route for the User List screen.
 *
 * This route is used with Jetpack Navigation to define and navigate to the User List screen.
 *
 * Features:
 * - Lightweight and immutable, designed for use with the navigation system.
 * - Marked as [Serializable], enabling it to be passed between navigation destinations if needed.
 *
 * Usage Example:
 * ```kotlin
 * val navController: NavHostController = rememberNavController()
 * navController.navigate(UserListRoute)
 * ```
 */
@Serializable
data object UserListRoute

fun NavGraphBuilder.userListScreen(onUserClick: (String) -> Unit) {
    composable<UserListRoute> {
        UserListScreen(onUserClick = onUserClick)
    }
}