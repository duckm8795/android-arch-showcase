package com.arch.user.data.repository.userlist

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.arch.user.data.mapper.toUserEntity
import com.arch.user.data.datasource.local.dao.UserDao
import com.arch.user.data.datasource.local.entity.UserEntity
import com.arch.user.data.datasource.remote.service.UserService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * `UserMediator` is a [RemoteMediator] implementation that synchronizes user data between a remote service
 * ([UserService]) and a local database ([UserDao]). It supports paginated data loading and caching.
 *
 * @param userService A [UserService] instance to fetch user data from a remote source.
 * @param userDao A [UserDao] instance to manage user data in the local database.
 * @param userListPreference A [UserListPreference] to store and manage metadata related to the user list,
 * such as the last updated timestamp.
 *
 * Features:
 * - Supports paginated loading using [LoadType] (REFRESH, PREPEND, APPEND).
 * - Manages caching behavior with [initialize] and `CACHE_TIMEOUT`.
 * - Handles refresh and append operations to maintain consistency between local and remote data sources.
 *
 * Paging Behavior:
 * - **REFRESH**:
 *   - Resets the pagination and fetches the initial set of data from the remote source.
 *   - Deletes existing local data if needed.
 *   - Updates the last refreshed timestamp in [userListPreference].
 * - **PREPEND**:
 *   - Skips loading additional data before the first page (end of pagination is reached).
 * - **APPEND**:
 *   - Fetches the next page of data starting from the last item's ID.
 *
 * Methods:
 * - [load]:
 *   - Loads data based on the [LoadType] and updates the local database with the fetched data.
 *   - Determines if the pagination has reached its end.
 * - [initialize]:
 *   - Decides whether to perform an initial refresh or skip it based on the last updated time.
 *
 * Companion Object:
 * - `CACHE_TIMEOUT`: Duration in milliseconds after which cached data is considered stale (1 hour).
 * - `INITIAL_SINCE`: The initial offset value for paginated requests.
 *
 * Example Usage:
 * ```kotlin
 * val pager = Pager(
 *     config = PagingConfig(pageSize = 20),
 *     remoteMediator = UserMediator(userService, userDao, userListPreference),
 *     pagingSourceFactory = { userDao.getPagingSource() }
 * )
 * val usersFlow: Flow<PagingData<User>> = pager.flow
 * ```
 *
 * Error Handling:
 * - Returns [MediatorResult.Error] on exceptions during data loading.
 *
 * Notes:
 * - This class uses [ExperimentalPagingApi] for advanced paging functionality.
 * - The `upsertAndDeleteAll` method in [UserDao] ensures atomic updates for local data.
 * - Ensure proper error handling and logging for exceptions to enhance debugging and user experience.
 */
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