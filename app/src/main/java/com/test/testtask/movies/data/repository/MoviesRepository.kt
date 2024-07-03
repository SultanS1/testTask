package com.test.testtask.movies.data.repository

import com.test.testtask.movies.data.entity.MovieEntity

interface MoviesRepository {

    suspend fun getMovies(): List<MovieEntity>

}