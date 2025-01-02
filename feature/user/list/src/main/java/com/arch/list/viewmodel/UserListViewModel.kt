package com.arch.list.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arch.common.viewmodel.BaseViewModel
import com.arch.common.viewmodel.NoEvent
import com.arch.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase,
) : BaseViewModel<UserListUiState, NoEvent>(
    initialUiState = UserListUiState(),
) {
    val userPaging = getUsersUseCase()
        .cachedIn(viewModelScope)

    override fun setLoading(isLoading: Boolean) {
        if (!uiState.isRefreshing) {
            super.setLoading(isLoading)
        }
    }

    fun setRefreshing(isRefreshing: Boolean) {
        updateUiState { copy(isRefreshing = isRefreshing) }
    }
}

internal data class UserListUiState(
    val isRefreshing: Boolean = false,
)