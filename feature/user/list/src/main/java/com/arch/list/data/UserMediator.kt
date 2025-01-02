package com.arch.list.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.arch.list.data.mapper.toUserEntity
import com.arch.list.data.preference.UserListPreference
import com.arch.user.data.datasource.local.dao.UserDao
import com.arch.user.data.datasource.local.entity.UserEntity
import com.arch.user.data.datasource.remote.service.UserService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class UserMediator @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao,
    private val userListPreference: UserListPreference,
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        try {
            val since = when (loadType) {
                LoadType.REFRESH -> {
                    INITIAL_SINCE
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    state.lastItemOrNull()?.id
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
            val responses = userService.getUsers(
                perPage = state.config.pageSize,
                since = since,
            )
            val entities = responses.map { response -> response.toUserEntity() }
            val isRefresh = loadType == LoadType.REFRESH

            userDao.upsertAndDeleteAll(
                needToDelete = isRefresh,
                entities = entities,
            )
            if (isRefresh) {
                userListPreference.lastUpdatedUserList = System.currentTimeMillis()
            }

            return MediatorResult.Success(
                endOfPaginationReached = responses.size < state.config.pageSize
            )
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val currentTimeMillis = System.currentTimeMillis()
        val lastUpdated = userListPreference.lastUpdatedUserList
        return if (currentTimeMillis - lastUpdated <= CACHE_TIMEOUT) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    companion object {
        private val CACHE_TIMEOUT = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        private const val INITIAL_SINCE = 0
    }
}