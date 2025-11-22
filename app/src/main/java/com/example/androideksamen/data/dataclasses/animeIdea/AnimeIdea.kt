package com.example.androideksamen.data.dataclasses.animeIdea

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androideksamen.data.database.Genre

@Entity
data class AnimeIdea(
    @PrimaryKey
    val id: Int,
    val title: String,
    val genre: Genre,
    val synopsis: String
)