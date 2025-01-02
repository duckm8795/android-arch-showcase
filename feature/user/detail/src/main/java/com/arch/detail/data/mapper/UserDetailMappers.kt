package com.arch.detail.data.mapper

import com.arch.common.extension.orZero
import com.arch.domain.model.UserDetail
import com.arch.user.data.datasource.remote.response.UserDetailResponse

internal fun UserDetailResponse.toModel() = UserDetail(
    username = login.orEmpty(),
    avatarUrl = avatarUrl.orEmpty(),
    location = location.orEmpty(),
    followers = followers.orZero(),
    following = following.orZero(),
    url = htmlUrl.orEmpty(),
)