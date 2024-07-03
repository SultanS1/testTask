package com.test.testtask.characters.data.repository

import com.test.testtask.characters.data.dto.toEntity
import com.test.testtask.characters.data.entity.CharacterEntity
import com.test.testtask.characters.data.local.CharactersDao
import com.test.testtask.characters.data.remote.CharactersApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(
    private val dao: CharactersDao,
    private val remote: CharactersApi,
    private val ioDispatcher: CoroutineDispatcher
): CharactersRepository {

    override suspend fun getCharacters(number: Int): List<CharacterEntity> = withContext(ioDispatcher) {
        val localCharacters = dao.getCharacters()
        if (localCharacters.isNotEmpty()) {
            return@withContext localCharacters.filter { character ->
                character.films.any { filmUrl ->
                    // Extract film number from URL and compare
                    val regex = Regex("""/films/(\d+)/""")
                    val matchResult = regex.find(filmUrl)
                    val num = matchResult?.groupValues?.get(1)?.toIntOrNull()
                    num == number
                }
            }
        }
        try {
            val remoteResult = remote.getCharacters()
            val characterEntities = remoteResult.results.map { it.toEntity() }
            dao.insert(characterEntities)
            return@withContext characterEntities.filter { character ->
                character.films.any { filmUrl ->
                    // Extract film number from URL and compare
                    val regex = Regex("""/films/(\d+)/""")
                    val matchResult = regex.find(filmUrl)
                    val num = matchResult?.groupValues?.get(1)?.toIntOrNull()
                    num == number
                }
            }
        } catch (e: Exception) {
            return@withContext emptyList<CharacterEntity>()
        }
    }

}