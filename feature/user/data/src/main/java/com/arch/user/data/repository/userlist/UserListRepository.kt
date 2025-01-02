package com.arch.user.data.repository.userlist

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.arch.domain.model.User
import com.arch.domain.repository.UserListRepository
import com.arch.user.data.mapper.toUserModel
import com.arch.user.data.datasource.local.dao.UserDao
import com.arch.user.data.datasource.remote.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [UserListRepository] that provides paginated user data by combining
 * local and remote data sources using Jetpack Paging 3.
 *
 * @param userService A [UserService] instance to fetch user data from a remote source.
 * @param userDao A [UserDao] instance to query user data from the local database.
 * @param userListPreference A [UserListPreference] to manage user-specific preferences related to the user list.
 *
 * Features:
 * - Implements the [getUsers] function to provide a [Flow] of [PagingData] containing [User] models.
 * - Combines local and remote data sources using a [UserMediator] to manage data synchronization.
 * - Maps database entities to the [User] model before exposing them to the UI layer.
 *
 * Paging Configuration:
 * - `pageSize`: Number of items loaded per page (20).
 * - `enablePlaceholders`: Disabled to avoid showing placeholders for unloaded data.
 * - `prefetchDistance`: Number of items to load in advance (half of the page size).
 * - `initialLoadSize`: Size of the initial load (same as the page size).
 *
 * Components:
 * - [Pager]: Configures the paging behavior, combining a remote mediator and a paging source.
 * - [UserMediator]: Synchronizes remote and local data.
 * - [userDao.getPagingSource]: Provides the paging source for local data.
 * - [entity.toUserModel]: Maps database entities to the [User] model.
 *
 * Example Usage:
 * ```kotlin
 * @Inject lateinit var userListRepository: UserListRepository
 *
 * val usersFlow: Flow<PagingData<User>> = userListRepository.getUsers()
 * ```
 *
 * Companion Object:
 * - `PAGE_SIZE`: Defines the number of items per page (20).
 *
 * Notes:
 * - This implementation uses [ExperimentalPagingApi] to leverage the remote mediator functionality.
 * - Ensure proper error handling in [UserMediator] to gracefully handle network or database failures.
 */

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