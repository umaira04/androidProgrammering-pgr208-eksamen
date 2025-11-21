package com.example.androideksamen.data.dataclasses.anime

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("mal_id")
    val id: Int,
    val name: String?
)
