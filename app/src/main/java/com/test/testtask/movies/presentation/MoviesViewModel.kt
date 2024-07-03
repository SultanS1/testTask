package com.test.testtask.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtask.movies.data.entity.toUi
import com.test.testtask.movies.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MoviesRepository
): ViewModel() {

    private val _movies = MutableStateFlow<List<MoviesUiState>>(listOf())
    val movies: StateFlow<List<MoviesUiState>> get() = _movies

    fun getMovies(){
        viewModelScope.launch{
            val result = repository.getMovies()
            _movies.value = result.map { it.toUi() }
        }
    }

}