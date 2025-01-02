package com.arch.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.arch.detail.nav.UserDetailRoute
import com.arch.detail.ui.mapper.toUiState
import com.arch.domain.model.UserDetail
import com.arch.domain.usecase.GetUserDetailUseCase
import com.arch.testing.BaseViewmodelTest
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class UserDetailViewModelTest: BaseViewmodelTest() {
    private val getUserDetailsUseCase = mockk<GetUserDetailUseCase>()

    private lateinit var viewModel: UserDetailViewModel

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    @Test
    fun `test get userDetail with valid username should return success`() = runTest {
        val username = "username"
        val userDetailRoute = UserDetailRoute(username = username)
        val userDetails = UserDetail(
            username = "username",
            avatarUrl = "avatarUrl",
            country = "country",
            followers = 0,
            following = 0,
            url = "url",
        )
        coEvery { getUserDetailsUseCase(username) } returns flowOf(userDetails)
        every { savedStateHandle.toRoute<UserDetailRoute>() } returns userDetailRoute

        viewModel = UserDetailViewModel(
            savedStateHandle = savedStateHandle,
            dispatchersProvider = dispatchersProvider,
            getUserDetailUseCase = getUserDetailsUseCase,
        )
        advanceUntilIdle()

        viewModel.uiStateFlow.test {
            expectMostRecentItem() shouldBe userDetails.toUiState()
        }
    }
}