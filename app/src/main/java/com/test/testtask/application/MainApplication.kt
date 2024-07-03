package com.test.testtask.application

import android.app.Application
import com.test.testtask.characters.di.charactersModule
import com.test.testtask.movies.di.moviesModule
import com.test.testtask.planet.di.characterWorldModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(moviesModule, charactersModule, characterWorldModule))
            androidContext(this@MainApplication)
        }
    }
}