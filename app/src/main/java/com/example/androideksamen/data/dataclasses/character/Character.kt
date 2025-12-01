package com.example.androideksamen.data.dataclasses.character

import com.example.androideksamen.data.dataclasses.Images
import com.google.gson.annotations.SerializedName

// Her har vi valgt å ha alle dataklassene relatert til Character.
// Grunnen til dette er at vi kun bruker 5 felt fra Character endepunktet i APIet
// Images bruker samme dataklasse som Anime, så den blir kalt på fra ApiImages
data class CharacterResponse(
    val data: List<Character>
)

data class FilteredCharacterResponse(
    val data: List<MainCharacter>
)

data class Character(
    @SerializedName("mal_id")
    val id: Int,

    // CharacterImage returnerer en liste på samme måte som Anime,
    // så vi har fulgt samme struktur som i
    @SerializedName("images")
    val characterImage: Images?,

    val name: String,

    @SerializedName("name_kanji")
    val nameJapanese: String?,

    val url: String?
)

// MainCharacter bruker de samme feltene som Character,
// men legger til role for å kunne filtrere etter hovedkarakterer
data class MainCharacter(
    val character: Character,

    val role: String?
)