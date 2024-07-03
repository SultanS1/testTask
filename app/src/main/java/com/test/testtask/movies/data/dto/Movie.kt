package com.test.testtask.movies.data.dto

import com.google.gson.annotations.SerializedName
import com.test.testtask.movies.data.entity.MovieEntity

data class Movie(
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
    val edited: String,
    val url: String
)

fun Movie.toEntity(): MovieEntity{
    return MovieEntity(
        url = url,
        title = title,
        episodeId =  episodeId,
        openingCrawl = openingCrawl,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        characters = characters,
        planets = planets,
        starships = starships,
        vehicles = vehicles,
        species = species,
        created = created,
        edited = edited
    )
}