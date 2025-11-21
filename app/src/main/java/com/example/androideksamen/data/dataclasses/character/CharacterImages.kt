package com.example.androideksamen.data.dataclasses.character

import com.google.gson.annotations.SerializedName


data class CharacterImages(
    val jpg: JpgImage
)


data class JpgImage(
    @SerializedName("image_url")
    val imageUrl: String
)