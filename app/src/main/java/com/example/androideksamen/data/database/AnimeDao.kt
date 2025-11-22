package com.example.androideksamen.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_idea")
    suspend fun getAnimeIdeas(): List<AnimeDB>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeIdea(animeIdea: AnimeDB): Long
}