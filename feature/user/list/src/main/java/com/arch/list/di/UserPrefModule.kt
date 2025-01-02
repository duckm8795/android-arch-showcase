package com.arch.list.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class UserPrefModule {
    @Provides
    @Singleton
    fun provideUserListSharePref(@ApplicationContext context: Context) = context.getSharedPreferences("user_list_pref", Context.MODE_PRIVATE)
}