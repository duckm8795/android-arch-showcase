package com.arch.user.data.di

import com.arch.domain.repository.UserDetailRepository
import com.arch.domain.repository.UserListRepository
import com.arch.user.data.repository.userdetail.UserDetailRepositoryImpl
import com.arch.user.data.repository.userlist.UserListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserRepositoryModule {

    @Binds
    fun bindUserRepository(impl: UserListRepositoryImpl): UserListRepository

    @Binds
    fun bindUserDetailRepository(impl: UserDetailRepositoryImpl): UserDetailRepository
}