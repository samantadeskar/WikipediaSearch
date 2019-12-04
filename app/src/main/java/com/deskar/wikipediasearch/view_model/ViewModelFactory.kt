package com.deskar.wikipediasearch.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deskar.wikipediasearch.model.SearchDataSource

class ViewModelFactory(
    private val repository:SearchDataSource
) : ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}