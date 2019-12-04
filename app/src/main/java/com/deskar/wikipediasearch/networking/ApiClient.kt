package com.deskar.wikipediasearch.networking

import com.deskar.wikipediasearch.constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var apiService: ApiService? = null

    fun build(): ApiService? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        apiService = retrofit.create(
            ApiService::class.java
        )

        return apiService as ApiService

    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}