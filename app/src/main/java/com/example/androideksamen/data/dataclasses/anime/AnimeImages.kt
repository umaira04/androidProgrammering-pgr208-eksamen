package com.example.androideksamen.data.dataclasses.anime

import com.google.gson.annotations.SerializedName

data class AnimeImages(
    val jpg: JpgImage?
)

data class JpgImage(
    @SerializedName("large_image_url")
    val imageUrl: String? = null
)