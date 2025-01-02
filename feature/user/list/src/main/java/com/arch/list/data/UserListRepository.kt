package com.arch.list.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.arch.domain.model.User
import com.arch.list.data.mapper.toUserModel
import com.arch.list.data.preference.UserListPreference
import com.arch.user.data.datasource.local.dao.UserDao
import com.arch.user.data.datasource.remote.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface UserListRepository {
    fun getUsers(): Flow<PagingData<User>>
}

@OptIn(ExperimentalPagingApi::class)
internal class UserListRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao,
    private val userListPreference: UserListPreference
) : UserListRepository {

    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = PAGE_SIZE / 2,
                initialLoadSize = PAGE_SIZE,
            ),
            remoteMediator = UserMediator(
                userService = userService,
                userDao = userDao,
                userListPreference = userListPreference,
            ),
            pagingSourceFactory = {
                userDao.getPagingSource()
            },
        ).flow.map { pagingData -> pagingData.map { entity -> entity.toUserModel() } }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}