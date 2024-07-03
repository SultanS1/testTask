package com.test.testtask.movies.di

import com.test.testtask.common.Constants
import com.test.testtask.core.appDatabase.AppDatabase
import com.test.testtask.movies.data.remote.MoviesApi
import com.test.testtask.movies.data.repository.MoviesRepository
import com.test.testtask.movies.data.repository.MoviesRepositoryImpl
import com.test.testtask.movies.presentation.MoviesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val moviesModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single { getRetrofit() }
    single<MoviesRepository> { MoviesRepositoryImpl(AppDatabase.getDatabase(androidContext()).movieDao(), getAuthApi(get()), get())}
    viewModel { MoviesViewModel(get()) }
}

fun getRetrofit(): Retrofit{
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getAuthApi(retrofit: Retrofit): MoviesApi {
    return retrofit.create(MoviesApi::class.java)
}