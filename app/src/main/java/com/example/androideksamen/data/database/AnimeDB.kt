package com.example.androideksamen.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_idea")
data class AnimeDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val synopsis: String,
    val genre: Genre
)
