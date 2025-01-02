package com.arch.common.di

import com.arch.common.provider.DispatchersProvider
import com.arch.common.provider.DispatchersProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DispatcherModule {

    @Binds
    @Singleton
    fun bindDispatchersProvider(impl: DispatchersProviderImpl): DispatchersProvider
}