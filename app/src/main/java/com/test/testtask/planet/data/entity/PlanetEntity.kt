package com.test.testtask.planet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.testtask.planet.presentation.PlanetUiState

@Entity("planets")
data class PlanetEntity(
    @PrimaryKey
    val name: String,
    val rotation_period: String,
    val orbital_period: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surface_water: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String,
)

fun PlanetEntity.toUi(): PlanetUiState{
    return PlanetUiState(
        name = name,
        diameter = diameter,
        climat = climate,
        gravity = gravity,
        place = terrain,
        population = population
    )
}
