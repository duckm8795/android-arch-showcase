package com.arch.list.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arch.common.viewmodel.BaseViewModel
import com.arch.common.viewmodel.NoEvent
import com.arch.common.viewmodel.NoUiState
import com.arch.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase,
) : BaseViewModel<NoUiState, NoEvent>(
    initialUiState = NoUiState,
) {
    val userPaging = getUsersUseCase()
        .cachedIn(viewModelScope)

}
