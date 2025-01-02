package com.arch.domain.usecase

import androidx.paging.PagingData
import com.arch.domain.model.User
import com.arch.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserListRepository,
) {
    operator fun invoke(): Flow<PagingData<User>> {
        return userRepository.getUsers()
    }
}
