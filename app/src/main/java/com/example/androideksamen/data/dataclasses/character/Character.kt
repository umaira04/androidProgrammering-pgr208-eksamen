package com.example.androideksamen.data.dataclasses.character

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("mal_id")
    val id: Int? = null,

    @SerializedName("images")
    val characterImage: CharacterImages,

    val name: String?,

    @SerializedName("name_kanji")
    val nameJapanese: String?
)