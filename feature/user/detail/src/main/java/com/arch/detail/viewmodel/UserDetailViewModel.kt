package com.arch.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.arch.common.provider.DispatchersProvider
import com.arch.common.viewmodel.BaseViewModel
import com.arch.common.viewmodel.NoEvent
import com.arch.detail.nav.UserDetailRoute
import com.arch.detail.ui.UserDetailUiState
import com.arch.detail.ui.mapper.toUiState
import com.arch.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class UserDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchersProvider: DispatchersProvider,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : BaseViewModel<UserDetailUiState, NoEvent>(
    initialUiState = UserDetailUiState(),
) {

    private val routeData: UserDetailRoute by lazy {
        savedStateHandle.toRoute<UserDetailRoute>()
    }

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        getUserDetailUseCase(routeData.username).collectSafe(
            context = dispatchersProvider.io,
            onError = {
                // handle later
            },
            hasLoading = true,
        ) { userDetail ->
            updateUiState { userDetail.toUiState() }
        }
    }
}