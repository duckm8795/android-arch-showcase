package com.arch.detail.ui

internal data class UserDetailUiState(
    val username: String = "",
    val avatarUrl: String = "",
    val country: String = "",
    val followers: String = "0",
    val following: String = "0",
    val url: String = "",
)