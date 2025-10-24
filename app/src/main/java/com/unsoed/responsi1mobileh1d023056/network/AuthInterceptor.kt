package com.unsoed.responsi1mobileh1d023056.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    // !!! GANTI DENGAN API TOKEN ANDA !!!
    private val apiToken = "3b0df84da7c64f22bb2b81ea1ba5e6fe"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Auth-Token", apiToken) //
            .build()
        return chain.proceed(request)
    }
}