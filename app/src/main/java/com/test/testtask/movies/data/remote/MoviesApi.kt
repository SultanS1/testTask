package com.test.testtask.movies.data.remote

import com.test.testtask.common.ApiResponse
import com.test.testtask.movies.data.dto.Movie
import retrofit2.http.GET

interface MoviesApi {

    @GET("api/films/")
    suspend fun getMovies(): ApiResponse<Movie>

}