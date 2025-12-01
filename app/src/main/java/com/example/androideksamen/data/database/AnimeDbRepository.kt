package com.example.androideksamen.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room

// AnimeDbRepository bruker SQL kommandoene fra AnimeDao for å utføre CRUD operasjoner
object AnimeDbRepository {

    // TILKOBLING TIL DATABASE
    private lateinit var _appDatabase: AppDatabase
    private val _animeDao by lazy { _appDatabase.animeDao() }

    fun initializeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "animeIdea-database"
        ).fallbackToDestructiveMigration().build() //
    }


    // CRUD OPERASJONER

    // Henter animeIde fra database
    suspend fun getAnimeIdeas(): List<AnimeIdea> {
        try {
            return _animeDao.getAnimeIdeas()
        } catch (e: Exception) {
            Log.d("getAnimeIdeasCatch", e.message.toString())
            return emptyList()
        }
    }

    // Sender animeIde til database
    suspend fun insertAnimeIdeas(animeIdea: AnimeIdea): Long {
        try {
            return _animeDao.insertAnimeIdea(animeIdea)
        } catch (e: Exception) {
            Log.d("insertAnimeIdeasCatch", e.message.toString())
            return -1L
        }
    }

    // Oppdaterer animeIde i database
    suspend fun updateAnimeIdea(animeIdea: AnimeIdea): Int {
        try {
            Log.d("updateAnimeIdea", animeIdea.toString())
            return _animeDao.updateAnimeIdea(animeIdea)
        } catch (e: Exception) {
            Log.d("updateAnimeIdeasCatch", e.message.toString())
            return -1
        }
    }

    // Sletter animeIde
    suspend fun deleteAnimeIdea(animeIdea: AnimeIdea): Int {
        try {
            return _animeDao.deleteAnimeIdea(animeIdea)
        } catch (e: Exception) {
            Log.d("deleteAnimeIdeasCatch", e.message.toString())
            return -1
        }
    }
}
