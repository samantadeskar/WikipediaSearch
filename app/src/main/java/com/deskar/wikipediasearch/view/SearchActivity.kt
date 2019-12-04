package com.deskar.wikipediasearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.deskar.wikipediasearch.R
import com.deskar.wikipediasearch.model.SearchDataSource
import com.deskar.wikipediasearch.model.SearchRepository
import com.deskar.wikipediasearch.networking.ApiClient
import com.deskar.wikipediasearch.networking.ApiService
import com.deskar.wikipediasearch.view.adapter.SearchAdapter
import com.deskar.wikipediasearch.view_model.SearchViewModel
import com.deskar.wikipediasearch.view_model.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private lateinit var searchRepository: SearchRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //setupViewModel()
        //val apiService: ApiService? = ApiClient.build()
        searchRepository = SearchRepository()
        viewModel = getViewModel()
        textView_search.setOnClickListener {
            searchQuery(editText_search.text.toString())
        }
    }

    private fun setupUI(adapter: SearchAdapter) {
        recycler_search.layoutManager = LinearLayoutManager(this)
        recycler_search.adapter = adapter
    }

    private fun searchQuery(query: String) {

        viewModel.loadSearchResults(query)
        viewModel.searchResults.observe(this, Observer {
            adapter = SearchAdapter(it, this)
            setupUI(adapter)
        })
        viewModel.responseTimeResult.observe(this, Observer {
            textView_searchTime.text = it.toString()
        })
    }

    private fun getViewModel(): SearchViewModel {

        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchViewModel(searchRepository) as T
            }
        })[SearchViewModel::class.java]

    }
}
