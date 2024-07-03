package com.test.testtask.movies.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.test.testtask.movies.presentation.MoviesUiState

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val url: String,
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val created: String,
    val edited: String
)

fun MovieEntity.toUi(): MoviesUiState{
    return MoviesUiState(
        episodeId = episodeId,
        filmName = title,
        directorName =  director,
        producerName = producer,
        date = releaseDate
    )
}