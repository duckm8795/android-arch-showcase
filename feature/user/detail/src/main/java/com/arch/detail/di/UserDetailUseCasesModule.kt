package com.arch.detail.di

import com.arch.detail.domain.usecase.GetUserDetailUseCaseImpl
import com.arch.domain.usecase.GetUserDetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserDetailUseCasesModule {

    @Binds
    fun bindGetUserDetailUseCase(impl: GetUserDetailUseCaseImpl): GetUserDetailUseCase
}