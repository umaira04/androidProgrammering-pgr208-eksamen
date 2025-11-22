package com.example.androideksamen.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room

object AnimeDbRepository {
    private lateinit var _appDatabase: AppDatabase
    private val _animeDao by lazy { _appDatabase.animeDao() }

    fun initializeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "animeIdea-database"
        ).build()
    }

    suspend fun getAnimeIdeas(): List<AnimeDB> {
        try {
            return _animeDao.getAnimeIdeas()
        } catch (e: Exception) {
            Log.d("getAnimeIdeasCatch", e.message.toString())
            return emptyList()
        }
    }

    suspend fun insertAnimeIdeas(animeIdea: AnimeDB): Long {
        try {
            return _animeDao.insertAnimeIdea(animeIdea)
        } catch (e: Exception) {
            Log.d("insertAnimeIdeasCatch", e.message.toString())
            return -1L
        }
    }


}