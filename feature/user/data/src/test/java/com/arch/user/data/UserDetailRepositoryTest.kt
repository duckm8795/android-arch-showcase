package com.arch.user.data

import app.cash.turbine.test
import com.arch.user.data.datasource.remote.response.UserDetailResponse
import com.arch.user.data.datasource.remote.service.UserService
import com.arch.user.data.mapper.toModel
import com.arch.user.data.repository.userdetail.UserDetailRepositoryImpl
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class UserDetailRepositoryTest {

    private val userService: UserService = mockk()

    private val userRepository = UserDetailRepositoryImpl(userService)


    @Test
    fun `test get user detail should return success`() = runTest {
        // Given
        val username = "username"
        val response = UserDetailResponse()
        val expected = response.toModel()
        coEvery { userService.getUserDetail(username) } returns response

        // When
        val result = userRepository.getUserDetail(username)

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }
}