package com.arch.showcase.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arch.detail.nav.navigateToUserDetail
import com.arch.detail.nav.userDetailScreen
import com.arch.list.nav.UserListRoute
import com.arch.list.nav.userListScreen

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
        userDetailScreen()
        userListScreen(
            onUserClick = { username ->
                navController.navigateToUserDetail(username)
            }
        )
    }
}
