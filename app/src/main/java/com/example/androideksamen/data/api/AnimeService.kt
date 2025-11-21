package com.example.androideksamen.data.api

import com.example.androideksamen.data.dataclasses.anime.AnimeByIdResponse
import com.example.androideksamen.data.dataclasses.anime.AnimeResponse
import com.example.androideksamen.data.dataclasses.character.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeService {

    @GET("anime")
    suspend fun getAllAnime(
    ): Response<AnimeResponse>

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): Response<AnimeByIdResponse>


    @GET("characters")
    suspend fun getAllCharacters(
    ) : Response<CharacterResponse>
}