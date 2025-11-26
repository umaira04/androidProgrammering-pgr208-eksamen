package com.example.androideksamen.data.dataclasses.anime

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("mal_id")
    val id: Int,

    val images: AnimeImages? = null,

    @SerializedName("title_english")
    val titleEnglish: String?,

    @SerializedName("title_japanese")
    val titleJapanese: String?,

    val year: Int? = null,

    val synopsis: String? = null,

    val genres: List<Genre>? = emptyList(),

    val score: Double? = null,

    val type: String? = null,

    val episodes: Int? = null,

    val duration: String? = null,

    val url: String? = null,

    )