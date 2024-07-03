package com.test.testtask.core.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.testtask.characters.data.dto.Character
import com.test.testtask.characters.data.entity.CharacterEntity
import com.test.testtask.characters.data.local.CharactersDao
import com.test.testtask.common.ListOfStringConverter
import com.test.testtask.movies.data.entity.MovieEntity
import com.test.testtask.movies.data.local.MoviesDao
import com.test.testtask.planet.data.entity.PlanetEntity
import com.test.testtask.planet.data.local.PlanetDao

@Database(entities = [MovieEntity::class, CharacterEntity::class, PlanetEntity::class], version = 1)
@TypeConverters(ListOfStringConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    abstract fun charactersDao(): CharactersDao

    abstract fun planetDao(): PlanetDao
    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Database").build()
                INSTANCE = instance
                return instance
            }
        }

    }

}