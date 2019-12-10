package com.deskar.wikipediasearch.networking

import com.deskar.wikipediasearch.model.SearchResult
import com.deskar.wikipediasearch.model.SearchInformation
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val searchResult: List<SearchResult>?,
    val searchInformation: SearchInformation?,
    val msg: String?
)