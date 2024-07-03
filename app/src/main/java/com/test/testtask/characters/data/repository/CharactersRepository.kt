package com.test.testtask.characters.data.repository

import com.test.testtask.characters.data.entity.CharacterEntity

interface CharactersRepository {

    suspend fun getCharacters(number: Int): List<CharacterEntity>

}