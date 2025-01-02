package com.arch.user.data.di

import android.content.Context
import com.arch.user.data.datasource.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class UserDatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase = UserDatabase.Factory(context).create()

    @Provides
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()
}