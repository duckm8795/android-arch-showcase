package com.arch.user.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.arch.user.data.datasource.local.entity.UserEntity

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertAll(entities: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getPagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAll()

    @Transaction
    suspend fun upsertAndDeleteAll(entities: List<UserEntity>) {
        deleteAll()
        upsertAll(entities)
    }
}