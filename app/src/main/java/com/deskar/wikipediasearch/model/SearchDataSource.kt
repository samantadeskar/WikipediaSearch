package com.deskar.wikipediasearch.model

import com.deskar.wikipediasearch.networking.SearchCallback

interface SearchDataSource {
    fun getSearch(query: String, callback: SearchCallback)
}