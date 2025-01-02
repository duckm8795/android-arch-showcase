package com.arch.user.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("username")
    val username: String? = null,
    @ColumnInfo("avatar_url")
    val avatarUrl: String? = null,
    @ColumnInfo("url")
    val url: String? = null,
)