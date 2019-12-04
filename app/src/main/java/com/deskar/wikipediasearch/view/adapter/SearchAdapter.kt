package com.deskar.wikipediasearch.view.adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.deskar.wikipediasearch.R
import com.deskar.wikipediasearch.model.SearchResult
import kotlinx.android.synthetic.main.item_view.view.*


class SearchAdapter(
    private var searchResultList: List<SearchResult>,
    public val context: Context
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return SearchViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchResult = searchResultList[position]
        holder.bind(searchResult)
    }


    override fun getItemCount(): Int {
        return searchResultList.size
    }


    class SearchViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {


        fun bind(searchResult: SearchResult) {

            view.textView_searchResult.text = searchResult.title
            view.textView_searchResultURL.text = searchResult.link
            view.textView_htmlSnippet.text = searchResult.snippet

            view.textView_searchResultURL.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(searchResult.link))
                context.startActivity(intent)
            }

        }

    }


}
