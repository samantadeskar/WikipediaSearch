package com.deskar.wikipediasearch.di

import com.deskar.wikipediasearch.model.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SearchRepository((get())) }
}