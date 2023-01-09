package com.example.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun createOkHttpClient(): OkHttpClient {
    val bodyLogging = HttpLoggingInterceptor()
    bodyLogging.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        client.addNetworkInterceptor(bodyLogging)
    }
    return client.build()
}