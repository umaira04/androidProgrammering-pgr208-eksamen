package com.example.androideksamen.data.dataclasses.animeIdea

import com.example.androideksamen.data.database.DBGenre

data class AnimeIdea(
    val id: Int,
    val title: String,
    val DBGenre: DBGenre,
    val synopsis: String
)