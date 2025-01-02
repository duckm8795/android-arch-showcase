package com.arch.domain.model

data class UserDetail(
    val username: String,
    val avatarUrl: String,
    val country: String,
    val followers: Int,
    val following: Int,
    val url: String,
)