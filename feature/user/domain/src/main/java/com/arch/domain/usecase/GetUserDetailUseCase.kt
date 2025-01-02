package com.arch.domain.usecase

import com.arch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GetUserDetailUseCase {
    operator fun invoke(username: String): Flow<UserDetail>
}


