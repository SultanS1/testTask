package com.test.testtask.movies.data.repository

import com.test.testtask.movies.data.dto.toEntity
import com.test.testtask.movies.data.entity.MovieEntity
import com.test.testtask.movies.data.local.MoviesDao
import com.test.testtask.movies.data.remote.MoviesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val dao: MoviesDao,
    private val remote: MoviesApi,
    private val ioDispatcher: CoroutineDispatcher
): MoviesRepository {

    override suspend fun getMovies(): List<MovieEntity> = withContext(ioDispatcher) {
        val localMovies = dao.getMovie()

        if (localMovies.isNotEmpty()) {
            return@withContext localMovies
        }

        try {
            val remoteResult = remote.getMovies()
            val movieEntities = remoteResult.results.map { it.toEntity() }
            dao.insert(movieEntities)
            return@withContext movieEntities
        } catch (e: Exception) {
            return@withContext emptyList<MovieEntity>()
        }
    }

}