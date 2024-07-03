package com.test.testtask.application

import android.app.Application
import com.test.testtask.movies.di.moviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(moviesModule))
            androidContext(this@MainApplication)
        }
    }
}