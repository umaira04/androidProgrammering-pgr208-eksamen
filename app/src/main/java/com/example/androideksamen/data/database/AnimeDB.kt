package com.example.androideksamen.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// AnimeDB er en dataklasse som bestemmer hvilke verdier som skal brukes i anime_idea i db
@Entity(tableName = "anime_idea")
data class AnimeDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val synopsis: String,
    val genre: Genre
)
