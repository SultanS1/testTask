package com.test.testtask.planet.data.repository

import com.test.testtask.planet.data.entity.PlanetEntity

interface CharacterWorldRepository {

    suspend fun getPlanet(id: Int): PlanetEntity?

}