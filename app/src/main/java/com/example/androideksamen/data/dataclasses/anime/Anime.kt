package com.example.androideksamen.data.dataclasses.anime

import com.example.androideksamen.data.dataclasses.Images
import com.google.gson.annotations.SerializedName

// Anime dataklassen blir kalt på i data fra AnimeResponse og definerer verdiene vi tar i bruk fra APIet
data class Anime(
    @SerializedName("mal_id")
    val id: Int,

    // images returnerer en liste så her har vi valgt å lage en egen dataklasse
    val images: Images? = null,

    @SerializedName("title")
    val titleDefault: String?,

    @SerializedName("title_japanese")
    val titleJapanese: String?,

    val year: Int? = null,

    val synopsis: String? = null,

    // genres returnerer en liste så her har vi laget en egen dataklasse for genres
    val genres: List<Genre>? = emptyList(),

    val score: Double? = null,

    val type: String? = null,

    val episodes: Int? = null,

    val duration: String? = null,

    val url: String? = null
)