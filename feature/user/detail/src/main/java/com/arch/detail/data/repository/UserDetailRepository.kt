package com.arch.detail.data.repository

import com.arch.detail.data.mapper.toModel
import com.arch.domain.model.UserDetail
import com.arch.user.data.datasource.remote.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal interface UserDetailRepository {
    fun getUserDetail(username: String): Flow<UserDetail>
}

internal class UserDetailRepositoryImpl @Inject constructor(
    private val userService: UserService,
) : UserDetailRepository {

    override fun getUserDetail(username: String): Flow<UserDetail> = flow {
        emit(userService.getUserDetails(username).toModel())
    }
}