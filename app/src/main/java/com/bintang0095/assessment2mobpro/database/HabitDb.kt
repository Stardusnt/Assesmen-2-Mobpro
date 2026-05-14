package com.bintang0095.assessment2mobpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bintang0095.assessment2mobpro.model.Habit

@Database(
    entities = [Habit::class],
    version = 1,
    exportSchema = false
)

abstract class HabitDb : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {

        @Volatile
        private var INSTANCE: HabitDb? = null

        fun getInstance(
            context: Context
        ): HabitDb {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(

                    context.applicationContext,

                    HabitDb::class.java,

                    "habit_db"

                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}