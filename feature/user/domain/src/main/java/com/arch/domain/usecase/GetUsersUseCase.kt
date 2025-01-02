package com.arch.domain.usecase

import androidx.paging.PagingData
import com.arch.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {
    operator fun invoke(): Flow<PagingData<User>>
}

