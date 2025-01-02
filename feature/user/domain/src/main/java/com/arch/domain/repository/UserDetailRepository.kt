package com.arch.domain.repository

import com.arch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    fun getUserDetail(username: String): Flow<UserDetail>
}