package com.test.testtask.planet.data.dto

import com.test.testtask.planet.data.entity.PlanetEntity

data class Planet(
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
    val url: String
)

fun Planet.toEntity(): PlanetEntity{
    return PlanetEntity(
        name = name,
        rotation_period = rotation_period,
        orbital_period = orbital_period,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surface_water = surface_water,
        population = population,
        residents = residents,
        films = films,
        created = created,
        edited = edited,
        url = url)
}

