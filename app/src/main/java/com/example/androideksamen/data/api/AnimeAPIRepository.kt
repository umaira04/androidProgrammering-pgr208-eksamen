package com.example.androideksamen.data.api

import android.util.Log
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.character.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeAPIRepository {

    //oppretter klient
    private val _httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    //oppretter retrofit objekt
    private val _retrofit = Retrofit.Builder()
        .client(_httpClient)
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _animeService = _retrofit.create(AnimeService::class.java)

    suspend fun getAllAnime(): List<Anime> {
        try {
            val response = _animeService.getAllAnime()
            if (response.isSuccessful) {
                return response.body()?.data ?: emptyList()
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            Log.d("getAllAnimeCatch", e.toString())
            return emptyList()
        }
    }

    suspend fun getAnimeById(id: Int): Anime? {
        try {
            val response = _animeService.getAnimeById(id)
            if (response.isSuccessful) {
                return response.body()?.data
            } else {
                return null
            }
        } catch (e: Exception) {
            Log.d("getAnimeByIdCATCH", e.toString())
            return null
        }
    }

    suspend fun getAllCharacters(): List<Character> {
        try {
            val response = _animeService.getAllCharacters()
            if (response.isSuccessful) {
                return response.body()?.data ?: emptyList()
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            Log.d("getAnimeByIdCATCH", e.toString())
            return emptyList()
        }
    }
}