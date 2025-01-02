package com.arch.network.provider

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface RetrofitsProvider {
    fun provideRetrofit(baseUrl: String): Retrofit
}

internal class RetrofitsProviderImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
) : RetrofitsProvider {

    override fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

