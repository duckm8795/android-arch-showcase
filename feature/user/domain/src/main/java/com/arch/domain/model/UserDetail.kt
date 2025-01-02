package com.arch.domain.model

data class UserDetail(
    val username: String,
    val avatarUrl: String,
    val location: String,
    val followers: Int,
    val following: Int,
    val url: String,
)