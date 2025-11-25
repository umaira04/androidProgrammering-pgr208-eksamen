package com.example.androideksamen.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import kotlin.Exception

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

    suspend fun getAnimeIdeaById(id: Int): AnimeDB? {
        try {
            return _animeDao.getAnimeIdeaById(id)
        } catch (e: Exception) {
            return null
        }
    }

        suspend fun updateAnimeIdea(animeIdea: AnimeDB): Int {
            try {
                return _animeDao.updateAnimeIdea(animeIdea)
            } catch (e: Exception) {
                Log.d("updateAnimeIdeasCatch", e.message.toString())
                return -1 // ER DETTE RIKTIG? Skal det være long? Eller noe annet?
            }
        }

        suspend fun deleteAnimeIdea(animeIdea: AnimeDB): Int {
            try {
                return _animeDao.deleteAnimeIdea(animeIdea)
            } catch (e: Exception) {
                Log.d("deleteAnimeIdeasCatch", e.message.toString())
                return -1 // ER DETTE RIKTIG? Skal det være long? Eller noe annet?
            }
        }
    }
