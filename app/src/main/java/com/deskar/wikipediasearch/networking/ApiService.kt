package com.deskar.wikipediasearch.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1?")
    fun getSearchResult(
        @Query("q") query: String,
        @Query("cx") searchId: String,
        @Query("key") apiKey: String
    ): Call<SearchResponse>
}

