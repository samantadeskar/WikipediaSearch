package com.deskar.wikipediasearch

import android.app.Application
import com.deskar.wikipediasearch.di.networkingModule
import com.deskar.wikipediasearch.di.repositoryModule
import com.deskar.wikipediasearch.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WikipediaSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(networkingModule, viewModule, repositoryModule))
            androidContext(this@WikipediaSearchApp)
        }

    }

}