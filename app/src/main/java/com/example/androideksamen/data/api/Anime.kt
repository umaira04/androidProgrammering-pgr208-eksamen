package com.example.androideksamen.data.api

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("mal_id")
    val id: Int?,
    //TODO: IMAGE
    val title_english: String = "",
    val title_japanese: String = "",
    val year: Int = 0,
    val synopsis: String = "",
    val genres: String = "", //TODO: LEGG INN ARRAY
    val score: Double = 0.0,
    val type: String = "",
    val episodes: Int = 0,
    val duration: String ="",
    val url: String =""
)
