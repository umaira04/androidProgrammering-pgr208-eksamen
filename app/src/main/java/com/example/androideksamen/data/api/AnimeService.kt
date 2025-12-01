package com.example.androideksamen.data.api

import com.example.androideksamen.data.dataclasses.anime.AnimeByIdResponse
import com.example.androideksamen.data.dataclasses.anime.AnimeResponse
import com.example.androideksamen.data.dataclasses.character.CharacterResponse
import com.example.androideksamen.data.dataclasses.character.FilteredCharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// AnimeService utfører HTTP kall mot APIet og bruker URL laget i _retrofit i repository
interface AnimeService {

    // getAllAnime henter alle animeer i første "page" fra APi og returnerer en AnimeResponse
    // Denne funksjonen blir brukt for å vise alle anime i AnimeScreen
    @GET("anime")
    suspend fun getAllAnime(
    ): Response<AnimeResponse>


    // getAnimeById henter en anime etter en ID som den tar inn gjennom SearchScreen
    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): Response<AnimeByIdResponse>


    //getAllCharacters henter alle karakterer i første "page" fra API (Ekstraskjerm)
    // Denne funksjonen blir brukt i CharacterScreen for å vise alle karakterer
    @GET("characters")
    suspend fun getAllCharacters(
    ): Response<CharacterResponse>


    //getMainCharactersByAnimeId henter ut alle karakterer fra API filtrert etter AnimeID
    // Dette er en tillegsfunksjon vi har i searchScreen for å vise hovedkarakterer i til en Anime
    // Funksjonen returnerer i utgangspunktet alle karakterer i en anime, men vi filtrerer dette i repository
    @GET("anime/{id}/characters")
    suspend fun getMainCharactersByAnimeId(
        @Path("id") id: Int
    ): Response<FilteredCharacterResponse>
}