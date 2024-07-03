package com.test.testtask.characters.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.testtask.characters.data.entity.CharacterEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterEntity>

}