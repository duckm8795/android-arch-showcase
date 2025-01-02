package com.arch.domain.usecase

import com.arch.domain.model.UserDetail
import com.arch.domain.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userDetailRepository: UserDetailRepository,
) {
    operator fun invoke(username: String): Flow<UserDetail> = userDetailRepository.getUserDetail(username)
}