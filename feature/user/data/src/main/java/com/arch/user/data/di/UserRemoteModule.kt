package com.arch.user.data.di

import com.arch.common.constant.EnvConst
import com.arch.network.provider.OkHttpClientsProvider
import com.arch.user.data.datasource.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal class UserRemoteModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClientsProvider): Retrofit = Retrofit.Builder()
        .baseUrl(EnvConst.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.provideOkHttpClient())
        .build()

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)
}