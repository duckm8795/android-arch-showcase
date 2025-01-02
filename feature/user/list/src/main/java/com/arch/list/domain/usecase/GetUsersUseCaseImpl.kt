package com.arch.list.domain.usecase

import androidx.paging.PagingData
import com.arch.domain.model.User
import com.arch.domain.usecase.GetUsersUseCase
import com.arch.list.data.UserListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetUsersUseCaseImpl @Inject constructor(
    private val userRepository: UserListRepository,
): GetUsersUseCase {
    override operator fun invoke(): Flow<PagingData<User>> {
        return userRepository.getUsers()
    }
}