package com.arch.user.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

internal data class ErrorResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: String? = null,
)