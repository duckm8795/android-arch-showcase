package com.arch.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * `HeaderInterceptor` is an implementation of [Interceptor] that adds default headers to every HTTP request.
 *
 * Headers added:
 * - `"Accept": "application/json"`: Indicates that the client accepts responses in JSON format.
 * - `"Content-Type": "application/json"`: Specifies that the request body is in JSON format.
 *
 * This interceptor ensures consistent headers are included in all outgoing requests, which is especially
 * useful for APIs that expect these headers for proper communication.
 *
 * @constructor Uses [@Inject] for dependency injection, allowing integration with dependency injection frameworks
 * such as Hilt.
 */
internal class HeaderInterceptor @Inject constructor() : Interceptor {

    /**
     * Intercepts the outgoing request and adds the required headers.
     *
     * @param chain The [Interceptor.Chain] that provides access to the request and response.
     * @return The [Response] after the modified request is sent through the chain.
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}