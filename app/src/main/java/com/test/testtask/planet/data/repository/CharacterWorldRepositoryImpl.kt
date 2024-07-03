package com.test.testtask.planet.data.repository

import com.test.testtask.planet.data.dto.toEntity
import com.test.testtask.planet.data.entity.PlanetEntity
import com.test.testtask.planet.data.local.PlanetDao
import com.test.testtask.planet.data.remote.PlanetApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CharacterWorldRepositoryImpl(
    private val dao: PlanetDao,
    private val remote: PlanetApi,
    private val ioDispatcher: CoroutineDispatcher
): CharacterWorldRepository {

    override suspend fun getPlanet(id: Int): PlanetEntity? = withContext(ioDispatcher) {
        val planet = dao.getPlanet(id.toString())

        if (planet.isNotEmpty()) {
            return@withContext planet[0]
        }

        try {
            val remoteResult = remote.getPlanet(id)
            val planet = remoteResult.toEntity()
            dao.insert(planet)
            return@withContext planet
        } catch (e: Exception) {
            return@withContext null
        }
    }
}