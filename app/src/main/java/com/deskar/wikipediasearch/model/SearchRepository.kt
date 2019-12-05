package com.deskar.wikipediasearch.model

import com.deskar.wikipediasearch.constants.API_KEY
import com.deskar.wikipediasearch.constants.SEARCH_ID
import com.deskar.wikipediasearch.networking.ApiClient
import com.deskar.wikipediasearch.networking.SearchCallback
import com.deskar.wikipediasearch.networking.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository : SearchDataSource {

    private var call: Call<SearchResponse>? = null

    override fun getSearch(query: String, callback: SearchCallback) {
        call = ApiClient.build()?.getSearchResult(query, SEARCH_ID, API_KEY)
        call?.enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                response?.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it.searchResult, it.searchInformation)
                    } else {
                        callback.onError(it.msg)
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }


}