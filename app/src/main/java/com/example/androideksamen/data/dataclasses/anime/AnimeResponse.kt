package com.example.androideksamen.data.dataclasses.anime

data class AnimeResponse(
    val data: List<Anime> = emptyList()
)

data class AnimeByIdResponse(
    val data: Anime? = null
)
