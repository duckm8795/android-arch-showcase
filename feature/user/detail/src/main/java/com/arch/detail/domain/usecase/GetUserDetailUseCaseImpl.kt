package com.arch.detail.domain.usecase

import com.arch.detail.data.repository.UserDetailRepository
import com.arch.domain.model.UserDetail
import com.arch.domain.usecase.GetUserDetailUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetUserDetailUseCaseImpl @Inject constructor(
    private val userDetailRepository: UserDetailRepository,
): GetUserDetailUseCase {
    override fun invoke(username: String): Flow<UserDetail> = userDetailRepository.getUserDetail(username)
}