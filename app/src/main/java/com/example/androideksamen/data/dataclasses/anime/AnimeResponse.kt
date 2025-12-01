package com.example.androideksamen.data.dataclasses.anime

// Da APIet returnerer Anime i en liste som heter data har vi valgt å lage egne dataklasser
// som kun bruker data fra APIET
data class AnimeResponse(
    val data: List<Anime> = emptyList()
)

data class AnimeByIdResponse(
    val data: Anime? = null
)
