package com.arch.user.data.repository.userlist

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

internal class UserListPreference @Inject constructor(private val prefs: SharedPreferences) {

    var lastUpdatedUserList: Long
        get() = prefs.getLong(KEY_LAST_UPDATED_USER_LIST, 0L)
        set(value) = prefs.edit { putLong(KEY_LAST_UPDATED_USER_LIST, value) }

    companion object {
        private const val KEY_LAST_UPDATED_USER_LIST = "com.arch.user.list.last.updated"
    }
}
