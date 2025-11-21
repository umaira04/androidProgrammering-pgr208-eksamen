package com.example.androideksamen.data.api

import com.example.androideksamen.data.dataclasses.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeService {

    @GET("anime/")
    suspend fun getAllAnime(
    ): Response<AnimeResponse>

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): Response<>
}