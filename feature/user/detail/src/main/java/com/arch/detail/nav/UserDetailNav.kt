package com.arch.detail.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.arch.detail.ui.UserDetailScreen
import kotlinx.serialization.Serializable
/**
 * Represents the navigation route for the User Detail screen.
 *
 * @property username The username of the user whose details are to be displayed. Defaults to an empty string.
 *
 * Features:
 * - Marked as [Serializable] to enable passing route data in a type-safe manner between navigation destinations.
 * - Encapsulates route-specific arguments for better modularity and maintainability.
 *
 * Usage Example:
 * ```kotlin
 * val route = UserDetailRoute(username = "john_doe")
 * ```
 */
@Serializable
internal data class UserDetailRoute(
    val username: String = "",
)

/**
 * Registers the User Detail screen as a composable destination in the navigation graph.
 *
 * @param onBackClick A callback function triggered when the user navigates back.
 *
 * Features:
 * - Associates the `UserDetailRoute` with the `UserDetailScreen` composable.
 * - Handles navigation actions and route-specific arguments via [UserDetailRoute].
 *
 * Example Usage:
 * ```kotlin
 * val navController = rememberNavController()
 * NavHost(navController = navController, startDestination = "userList") {
 *     userDetailScreen(onBackClick = { navController.navigateUp() })
 * }
 * ```
 *
 * Notes:
 * - Ensure the parent navigation graph properly handles `onBackClick` for seamless navigation.
 */
fun NavGraphBuilder.userDetailScreen(onBackClick: () -> Unit) {
    composable<UserDetailRoute> {
        UserDetailScreen(onBackClick = onBackClick)
    }
}

/**
 * Extension function for [NavController] to navigate to the User Detail screen.
 *
 * @param username The username of the user whose details are to be displayed. Defaults to an empty string.
 * @param navOptions Optional [NavOptions] to customize the navigation behavior (e.g., singleTop, animations).
 *
 * Features:
 * - Provides a type-safe way to navigate to the User Detail screen with required arguments.
 * - Simplifies navigation by encapsulating route creation and navigation logic.
 *
 * Example Usage:
 * ```kotlin
 * val navController = rememberNavController()
 * navController.navigateToUserDetail(username = "john_doe")
 * ```
 *
 * Notes:
 * - Ensure that the `UserDetailRoute` is properly registered in the navigation graph using `userDetailScreen`.
 */

fun NavController.navigateToUserDetail(
    username: String = "",
    navOptions: NavOptions? = null,
) {
    navigate(route = UserDetailRoute(username), navOptions)
}
