package com.arch.network.provider

import com.arch.network.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal interface OkHttpClientsProvider {
    fun provideOkHttpClient(): OkHttpClient
}

internal class OkHttpClientsProviderImpl @Inject constructor(
    private val headerInterceptor: HeaderInterceptor,
) : OkHttpClientsProvider {

    override fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private companion object {
        private const val CONNECT_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
    }
}