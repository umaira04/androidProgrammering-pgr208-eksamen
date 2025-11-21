package com.example.androideksamen.data.database


data class AnimeDB(
    val id: Int = 0,
    val title: String,
    val genre: String, //TODO: ENDRE TIL ENUM?
    val synopsis: String
)
