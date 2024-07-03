package com.test.testtask.movies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.testtask.movies.data.entity.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getMovie(): List<MovieEntity>

}