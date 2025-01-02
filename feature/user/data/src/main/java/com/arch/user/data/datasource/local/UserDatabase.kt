package com.arch.user.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arch.user.data.datasource.local.dao.UserDao
import com.arch.user.data.datasource.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true,
)
internal abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "users.db"
    }

    class Factory(
        private val context: Context,
    ) {
        fun create(): UserDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = UserDatabase::class.java,
                name = DATABASE_NAME,
            ).build()
        }
    }
}