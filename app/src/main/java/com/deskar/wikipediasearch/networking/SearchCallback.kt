package com.deskar.wikipediasearch.networking

import com.deskar.wikipediasearch.model.SearchResult
import com.deskar.wikipediasearch.model.SearchInformation

interface SearchCallback {
    fun onSuccess(searchList: List<SearchResult>?, responseTime: SearchInformation?)
    fun onError(obj: Any?)
}