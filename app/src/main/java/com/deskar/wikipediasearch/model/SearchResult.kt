package com.deskar.wikipediasearch.model


import com.google.gson.annotations.SerializedName

data class SearchResult(
    val title:String,
    val link: String,
    val snippet: String
)