package com.example.androideksamen.data.database

import androidx.room.PrimaryKey


data class AnimeDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val genre: Genre,
    val synopsis: String
)
