package com.example.androideksamen.data.dataclasses

import com.google.gson.annotations.SerializedName

// images i APIet inneholder en liste med JPG og WEBP filer, sp vi har valgt å
// lage dataklasser som går inn i hver liste og henter til slutt "large_image_url"
data class Images(
    val jpg: JpgImage?
)

data class JpgImage(
    @SerializedName("image_url")
    val imageUrl: String? = null
)