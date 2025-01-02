package com.arch.domain.model

data class User(
    val id: Int = 0,
    val username: String = "",
    val avatarUrl: String = "",
    val url: String = "",
    val location: String = "",
)