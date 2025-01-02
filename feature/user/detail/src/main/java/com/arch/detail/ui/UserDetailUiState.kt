package com.arch.detail.ui

/**
 * Represents the UI state for the User Detail screen.
 *
 * @property username The username of the user being displayed.
 * @property avatarUrl The URL of the user's avatar image.
 * @property location The country of the user.
 * @property followers The number of followers the user has, represented as a [String]. Defaults to "0".
 * @property following The number of users the user is following, represented as a [String]. Defaults to "0".
 * @property url The URL of the user's blog or profile page.
 *
 * Features:
 * - Provides default values for all properties, ensuring a stable initial state.
 * - Used by the [UserDetailViewModel] to represent the state of the User Detail screen.
 * - Acts as a bridge between the ViewModel and the composable UI, encapsulating all necessary details.
 *
 * Usage Example:
 * ```kotlin
 * val initialState = UserDetailUiState()
 * val populatedState = UserDetailUiState(
 *     username = "john_doe",
 *     avatarUrl = "https://example.com/avatar.jpg",
 *     country = "USA",
 *     followers = "150",
 *     following = "200",
 *     url = "https://example.com/blog"
 * )
 * ```
 *
 * Notes:
 * - The string representations of `followers` and `following` allow flexibility in formatting (e.g., "1.5K").
 * - Ensure that all properties are properly mapped when converting from API responses or database entities.
 */

internal data class UserDetailUiState(
    val username: String = "",
    val avatarUrl: String = "",
    val location: String = "",
    val followers: String = "0",
    val following: String = "0",
    val url: String = "",
)