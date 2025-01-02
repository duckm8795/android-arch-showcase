package com.arch.user.data.mapper

import com.arch.common.extension.orZero
import com.arch.domain.model.User
import com.arch.user.data.datasource.local.entity.UserEntity
import com.arch.user.data.datasource.remote.response.UserResponse

internal fun UserResponse.toUserEntity(): UserEntity = UserEntity(
    id = id.orZero(),
    username = login.orEmpty(),
    avatarUrl = avatarUrl.orEmpty(),
    url = htmlUrl.orEmpty(),
)

internal fun UserEntity.toUserModel() = User(
    id = id.orZero(),
    username = username.orEmpty(),
    avatarUrl = avatarUrl.orEmpty(),
    url = url.orEmpty(),
)