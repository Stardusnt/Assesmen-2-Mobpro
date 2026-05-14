package com.bintang0095.assessment2mobpro.database

import androidx.room.*
import com.bintang0095.assessment2mobpro.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert
    suspend fun insertHabit(
        habit: Habit
    )

    @Update
    suspend fun updateHabit(
        habit: Habit
    )

    @Delete
    suspend fun deleteHabit(
        habit: Habit
    )

    @Query(
        "SELECT * FROM habit ORDER BY id DESC"
    )

    fun getAllHabit():
            Flow<List<Habit>>
}