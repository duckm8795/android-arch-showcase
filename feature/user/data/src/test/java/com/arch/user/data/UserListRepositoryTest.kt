package com.arch.user.data

import androidx.paging.PagingData
import androidx.paging.PagingSource
import app.cash.turbine.test
import com.arch.domain.model.User
import com.arch.user.data.repository.userlist.UserListPreference
import com.arch.user.data.datasource.local.dao.UserDao
import com.arch.user.data.datasource.local.entity.UserEntity
import com.arch.user.data.datasource.remote.service.UserService
import com.arch.user.data.repository.userlist.UserListRepositoryImpl
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class UserListRepositoryTest {

    private val userService: UserService = mockk()
    private val userDao: UserDao = mockk()
    private val userListPreference: UserListPreference = mockk()

    private val userRepository = UserListRepositoryImpl(userService, userDao, userListPreference)

    @Test
    fun `test get users should return success returns Users`() = runTest {
        val lastUpdateTimed = System.currentTimeMillis()
        val pagingSource = mockk<PagingSource<Int, UserEntity>>(relaxed = true)
        coEvery { userDao.getPagingSource() } returns pagingSource
        coEvery { userListPreference.lastUpdatedUserList } returns lastUpdateTimed

        val result = userRepository.getUsers()

        result.test {
            expectMostRecentItem() should beInstanceOf<PagingData<User>>()
        }
    }
}