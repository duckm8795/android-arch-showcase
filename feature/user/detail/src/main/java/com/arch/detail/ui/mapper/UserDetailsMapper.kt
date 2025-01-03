package com.arch.detail.ui.mapper

import com.arch.detail.ui.UserDetailUiState
import com.arch.domain.model.UserDetail

internal fun UserDetail.toUiState() = UserDetailUiState(
    username = username,
    avatarUrl = avatarUrl,
    location = location,
    followers = "$followers+",
    following = "$following+",
    url = url,
)