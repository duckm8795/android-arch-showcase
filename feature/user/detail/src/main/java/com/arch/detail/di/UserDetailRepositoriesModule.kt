package com.arch.detail.di

import com.arch.detail.data.repository.UserDetailRepository
import com.arch.detail.data.repository.UserDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserDetailRepositoriesModule {

    @Binds
    fun bindUserDetailRepository(impl: UserDetailRepositoryImpl): UserDetailRepository
}