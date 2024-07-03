package com.test.testtask.planet.di

import com.test.testtask.core.appDatabase.AppDatabase
import com.test.testtask.planet.data.remote.PlanetApi
import com.test.testtask.planet.data.repository.CharacterWorldRepository
import com.test.testtask.planet.data.repository.CharacterWorldRepositoryImpl
import com.test.testtask.planet.presentation.CharactersWorldViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val characterWorldModule = module {
    single<CharacterWorldRepository> { CharacterWorldRepositoryImpl(
        AppDatabase.getDatabase(androidContext()).planetDao(),
        getPlanetApi(get()),
        get()) }

    viewModel { CharactersWorldViewModel(get()) }
}

fun getPlanetApi(retrofit: Retrofit): PlanetApi {
    return retrofit.create(PlanetApi::class.java)
}