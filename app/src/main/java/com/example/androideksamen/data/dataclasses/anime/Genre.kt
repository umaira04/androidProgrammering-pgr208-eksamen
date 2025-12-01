package com.example.androideksamen.data.dataclasses.anime

import com.google.gson.annotations.SerializedName

// Genre returnerer en liste med sjangere med en ID

data class Genre(
    @SerializedName("mal_id")
    val id: Int? = null,
    val name: String?
)
