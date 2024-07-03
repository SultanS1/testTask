package com.test.testtask.planet.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.testtask.planet.data.entity.PlanetEntity

@Dao
interface PlanetDao {

    @Query("SELECT * FROM planets WHERE url LIKE '%' || :value || '%'")
    fun getPlanet(value: String): List<PlanetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: PlanetEntity)

}