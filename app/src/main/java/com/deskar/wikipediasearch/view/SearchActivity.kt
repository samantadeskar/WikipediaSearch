package com.deskar.wikipediasearch.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.deskar.wikipediasearch.R
import com.deskar.wikipediasearch.model.SearchRepository
import com.deskar.wikipediasearch.model.SearchResult
import com.deskar.wikipediasearch.view.adapter.SearchAdapter
import com.deskar.wikipediasearch.view_model.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recycler_search.layoutManager = LinearLayoutManager(this)

        textView_search.setOnClickListener {
            searchWikipedia(editText_search.text.toString())
        }
    }

    private fun searchWikipedia(query: String) {
        searchViewModel.loadSearchResults(query)
        setData()
    }

    private fun setData() {

        searchViewModel.isViewLoading.observe(this, viewLoadingObserver)
        searchViewModel.searchResults.observe(this, searchResultsObserver)
        searchViewModel.responseTimeResult.observe(this, responseTimeObserver)
        searchViewModel.onMessageError.observe(this, messageErrorObserver)
        searchViewModel.isEmptyList.observe(this, emptyListObserver)

    }

    private val viewLoadingObserver = Observer<Boolean> {
        val visibilityProgressBar = if (it) View.VISIBLE else View.INVISIBLE
        val visibilityTextViewSearch = if (it) View.INVISIBLE else View.VISIBLE
        progressBar.visibility = visibilityProgressBar
        textView_search.visibility = visibilityTextViewSearch
    }

    private val searchResultsObserver = Observer<List<SearchResult>> {
        adapter = SearchAdapter(it, this)
        setupUI(adapter)
    }

    @SuppressLint("SetTextI18n")
    private val responseTimeObserver = Observer<String> {
        textView_searchTime.text = getString(R.string.search_time) +
                it.toString() +
                getString(R.string.sec)
    }

    @SuppressLint("SetTextI18n")
    private val messageErrorObserver = Observer<Any> {
        recycler_search.visibility = View.INVISIBLE
        textView_messageError.visibility = View.VISIBLE
        textView_emptyList.visibility = View.INVISIBLE
        textView_messageError.text = getString(R.string.error) + it.toString()
    }

    private val emptyListObserver = Observer<Boolean> {
        recycler_search.visibility = View.INVISIBLE
        textView_messageError.visibility = View.INVISIBLE
        textView_emptyList.visibility = View.VISIBLE
    }

    private fun setupUI(adapter: SearchAdapter) {
        recycler_search.visibility = View.VISIBLE
        textView_messageError.visibility = View.INVISIBLE
        textView_emptyList.visibility = View.INVISIBLE
        recycler_search.adapter = adapter
    }

}
