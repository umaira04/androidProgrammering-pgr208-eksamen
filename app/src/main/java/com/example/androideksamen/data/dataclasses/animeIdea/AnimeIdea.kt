package com.example.androideksamen.data.dataclasses.animeIdea

import com.example.androideksamen.data.database.Genre

data class AnimeIdea(
    val id: Int,
    val title: String,
    val genre: Genre,
    val synopsis: String
)
