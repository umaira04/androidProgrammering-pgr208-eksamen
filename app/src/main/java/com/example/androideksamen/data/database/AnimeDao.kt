package com.example.androideksamen.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// AnimeDao er et interface som definerer hvilke SQL spørringer appen kan kjøre
// Vi har implementert full CRUD i dette prosjektet

@Dao
interface AnimeDao {

    // getAnimeIdeas henter alle animeer fra anime_idea i databasen
    // Ved første kjøring av programmet vil ikke getAnimeIdeas returnere noe
    @Query("SELECT * FROM anime_idea")
    suspend fun getAnimeIdeas(): List<AnimeDB>


    // insertAnimeIdea lagrer animeIdea til databasen
    // Funksjonen kjøres når man trykker lagre etter å ha laget en ny ide
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeIdea(animeIdea: AnimeDB): Long


    // updateAnimeIdea oppdaterer animeIdea.
    @Update
    suspend fun updateAnimeIdea(animeIdea: AnimeDB): Int


    // deleteAnimeIdea brukes for å slette animeIdea fra databasen
    @Delete
    suspend fun deleteAnimeIdea(animeIdea: AnimeDB): Int

}
