package com.example.androideksamen.data.dataclasses.character

import com.google.gson.annotations.SerializedName


data class CharacterResponse(
    val data: List<Character>
)

data class FilteredCharacterResponse(
    val data: List<MainCharacter>
)

data class Character(
    @SerializedName("mal_id")
    val id: Int,

    @SerializedName("images")
    val characterImage: CharacterImages?,

    val name: String,

    @SerializedName("name_kanji")
    val nameJapanese: String?,

    val url: String?
)


data class MainCharacter(
    val character: Character,
    val role: String?,

    )