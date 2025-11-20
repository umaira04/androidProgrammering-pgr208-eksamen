package com.example.androideksamen.data.dataclasses

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("mal_id")
    val id: Int? = null,
    val images: String,//TODO: FIX IMAGE
    val name: String,
    @SerializedName("name_kanji")
    val nameJapanese: String
)