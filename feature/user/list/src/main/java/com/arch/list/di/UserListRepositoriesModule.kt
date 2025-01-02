package com.arch.list.di

import com.arch.list.data.UserListRepository
import com.arch.list.data.UserListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserListRepositoriesModule {

    @Binds
    fun bindUserRepository(impl: UserListRepositoryImpl): UserListRepository
}