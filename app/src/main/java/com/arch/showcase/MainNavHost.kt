package com.arch.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arch.detail.nav.navigateToUserDetail
import com.arch.detail.nav.userDetailScreen
import com.arch.list.nav.UserListRoute
import com.arch.list.nav.userListScreen

/**
 * MainNavHost is the central navigation component of the app, defining the navigation graph and managing navigation
 * between screens. It initializes with a given [NavHostController] and [Modifier] for customization.
 *
 * @param modifier A [Modifier] for customizing the appearance or layout of the NavHost.
 * @param navController A [NavHostController] to handle navigation actions. Defaults to a newly created controller using [rememberNavController].
 *
 * Navigation graph:
 * - `UserListRoute`: The starting destination, showing a list of users.
 * - `userDetailScreen`: A detailed view of a user, navigated to when a user is selected from the user list.
 *
 * Navigation actions:
 * - `onBackClick`: Navigates back to the previous screen using [NavHostController.navigateUp].
 * - `onUserClick`: Navigates to the detail screen of a selected user by passing the user's username.
 */
@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = UserListRoute,
        modifier = modifier,
    ) {
        userDetailScreen(
            onBackClick = {
                navController.navigateUp()
            }
        )
        userListScreen(
            onUserClick = { username ->
                navController.navigateToUserDetail(username)
            }
        )
    }
}
