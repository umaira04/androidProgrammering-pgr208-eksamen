package com.example.androideksamen.data.dataclasses

import com.example.androideksamen.data.dataclasses.anime.Anime

data class AnimeResponse(
    val data: List<Anime> = emptyList()
)

