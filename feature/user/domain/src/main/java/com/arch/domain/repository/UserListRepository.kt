package com.arch.domain.repository

import androidx.paging.PagingData
import com.arch.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    fun getUsers(): Flow<PagingData<User>>
}