package com.arch.list.viewmodel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.arch.domain.model.User
import com.arch.domain.usecase.GetUsersUseCase
import com.arch.testing.BaseViewmodelTest
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UserListViewModelTest : BaseViewmodelTest() {

    private val getUserPagingUseCase: GetUsersUseCase = mockk()

    private lateinit var userListViewModel: UserListViewModel

    @Test
    fun `test get users is cached should return success`() = runTest {
        every { getUserPagingUseCase() } returns flowOf(PagingData.empty())
        userListViewModel = UserListViewModel(
            getUsersUseCase = getUserPagingUseCase,
        )

        val userPaging = userListViewModel.userPaging
        advanceUntilIdle()

        userPaging.test {
            expectMostRecentItem() should beInstanceOf<PagingData<User>>()
        }
    }

    @Test
    fun `test get users ui state should be updated success`() = runTest {
        every { getUserPagingUseCase() } returns flowOf(PagingData.empty())
        userListViewModel = UserListViewModel(
            getUsersUseCase = getUserPagingUseCase,
        )

        userListViewModel.setRefreshing(true)
        advanceUntilIdle()

        userListViewModel.uiState.isRefreshing shouldBe true
    }
}