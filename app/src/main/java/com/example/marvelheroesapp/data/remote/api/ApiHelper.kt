package com.example.marvelheroesapp.data.remote.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiHelper {


    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun getOkHttpClientWithStethoAndLoggingInterceptor() =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    fun createRetrofitConnection(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiEndpoint.MARVEL_BASE_URL)
        .client(getOkHttpClientWithStethoAndLoggingInterceptor())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}