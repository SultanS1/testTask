package com.test.testtask.core.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.testtask.common.ListOfStringConverter
import com.test.testtask.movies.data.entity.MovieEntity
import com.test.testtask.movies.data.local.MoviesDao

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(ListOfStringConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

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