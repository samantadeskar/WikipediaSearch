package com.deskar.wikipediasearch.model

import com.deskar.wikipediasearch.networking.OperationCallback

interface SearchDataSource {

    fun getSearch(query:String, callback: OperationCallback)
    fun cancel()

}