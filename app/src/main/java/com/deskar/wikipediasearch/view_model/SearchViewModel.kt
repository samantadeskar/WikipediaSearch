package com.deskar.wikipediasearch.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deskar.wikipediasearch.model.SearchResult
import com.deskar.wikipediasearch.model.SearchDataSource
import com.deskar.wikipediasearch.model.SearchInformation
import com.deskar.wikipediasearch.networking.OperationCallback

class SearchViewModel(
    private val repository: SearchDataSource
) : ViewModel() {

    private val _searchResults = MutableLiveData<List<SearchResult>>().apply {
        value = emptyList()
    }
    val searchResults: LiveData<List<SearchResult>> = _searchResults

    private val _responseTimeResult = MutableLiveData<String>()
    val responseTimeResult: LiveData<String> = _responseTimeResult

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadSearchResults(query:String) {
        _isViewLoading.postValue(true)
        repository.getSearch(query,
            object : OperationCallback {
            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(obj)
            }

            override fun onSuccess(searchList: List<SearchResult>?, responseTime: SearchInformation?) {
                _isViewLoading.postValue(false)
                if (searchList != null) {
                    if (!searchList.isEmpty() && responseTime != null) {
                        _searchResults.value = searchList
                        _responseTimeResult.value = responseTime.formattedSearchTime
                    } else {
                        _isEmptyList.postValue(true)
                    }
                }
            }
        })
    }


}
