package com.deskar.wikipediasearch.di

import com.deskar.wikipediasearch.constants.BASE_URL
import com.deskar.wikipediasearch.networking.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun getInterceptor() = HttpLoggingInterceptor().apply {
    HttpLoggingInterceptor.Level.BODY
}

private fun okHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getInterceptor())
    .build()

fun retrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createNetworkClient() = retrofitClient(okHttpClient())

fun provideApi(): ApiService {
    return createNetworkClient().create(ApiService::class.java)
}

val networkingModule = module {
    single { provideApi() }
}


