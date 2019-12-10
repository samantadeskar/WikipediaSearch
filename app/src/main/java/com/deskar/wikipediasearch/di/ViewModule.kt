package com.deskar.wikipediasearch.di

import com.deskar.wikipediasearch.view_model.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SearchViewModel(get())
    }
}