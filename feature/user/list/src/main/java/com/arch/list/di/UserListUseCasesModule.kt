package com.arch.list.di

import com.arch.domain.usecase.GetUsersUseCase
import com.arch.list.domain.usecase.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserListUseCasesModule {

    @Binds
    fun bindGetUsersUseCase(impl: GetUsersUseCaseImpl): GetUsersUseCase
}