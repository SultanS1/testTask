package com.test.testtask.characters.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.testtask.characters.presentation.CharacterUiState
import com.test.testtask.movies.data.entity.MovieEntity
import com.test.testtask.movies.presentation.MoviesUiState

@Entity("characters")
data class CharacterEntity(
    @PrimaryKey
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    var homeworld: String,
    val films: List<String>,
    var filmsNum: List<String> = listOf(),
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: String,
    val edited: String,
    val url: String
)


fun CharacterEntity.toUi(): CharacterUiState {
    return CharacterUiState(
        id = homeworld,
        name = name,
        gender = gender,
        birthdate = birthYear
    )
}
