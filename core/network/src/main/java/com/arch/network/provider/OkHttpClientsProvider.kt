package com.arch.network.provider

import com.arch.common.constant.EnvConst.IS_ENABLED_LOGGING
import com.arch.network.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Provides a mechanism for creating and configuring instances of [OkHttpClient].
 *
 * This interface abstracts the creation of an [OkHttpClient] to allow for different implementations
 * and to facilitate testing by enabling mock or custom clients.
 */
interface OkHttpClientsProvider {
    /**
     * Creates and provides an instance of [OkHttpClient] configured with the desired interceptors
     * and timeouts.
     *
     * @return A fully configured [OkHttpClient] instance.
     */
    fun provideOkHttpClient(): OkHttpClient
}

internal class OkHttpClientsProviderImpl @Inject constructor(
    private val headerInterceptor: HeaderInterceptor,
    private val httpLoggingInterceptor: HttpLoggingInterceptor,
) : OkHttpClientsProvider {

    override fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (IS_ENABLED_LOGGING) {
                    addInterceptor(httpLoggingInterceptor)
                }
            }
            .build()
    }

    private companion object {
        private const val CONNECT_TIMEOUT = 10L
        private const val READ_TIMEOUT = 10L
        private const val WRITE_TIMEOUT = 10L
    }
}