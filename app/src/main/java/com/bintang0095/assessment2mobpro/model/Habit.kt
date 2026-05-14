package com.bintang0095.assessment2mobpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")

data class Habit(

    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,

    val title: String,

    val category: String,

    val isDone: Boolean
)