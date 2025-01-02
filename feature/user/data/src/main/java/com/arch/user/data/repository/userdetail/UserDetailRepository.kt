package com.arch.user.data.repository.userdetail

import com.arch.domain.model.UserDetail
import com.arch.domain.repository.UserDetailRepository
import com.arch.user.data.datasource.remote.service.UserService
import com.arch.user.data.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UserDetailRepositoryImpl @Inject constructor(
    private val userService: UserService,
) : UserDetailRepository {

    override fun getUserDetail(username: String): Flow<UserDetail> = flow {
        emit(userService.getUserDetail(username).toModel())
    }
}