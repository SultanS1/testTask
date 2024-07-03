package com.test.testtask.characters.di

import com.test.testtask.characters.data.remote.CharactersApi
import com.test.testtask.characters.data.repository.CharactersRepository
import com.test.testtask.characters.data.repository.CharactersRepositoryImpl
import com.test.testtask.characters.presentation.CharactersViewModel
import com.test.testtask.core.appDatabase.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val charactersModule = module {
    single<CharactersRepository> { CharactersRepositoryImpl(AppDatabase.getDatabase(androidContext()).charactersDao(), getCharactersApi(get()), get()) }
    viewModel { CharactersViewModel(get()) }
}

fun getCharactersApi(retrofit: Retrofit): CharactersApi {
    return retrofit.create(CharactersApi::class.java)
}