package com.arch.user.data.di

import com.arch.common.constant.EnvConst
import com.arch.network.provider.RetrofitsProvider
import com.arch.user.data.datasource.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class UserRemoteModule {

    @Provides
    fun provideRetrofit(retrofitsProvider: RetrofitsProvider): Retrofit {
        return retrofitsProvider.provideRetrofit(EnvConst.BASE_URL)
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}