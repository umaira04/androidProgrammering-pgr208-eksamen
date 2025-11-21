package com.example.androideksamen.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androideksamen.data.dataclasses.animeIdea.AnimeIdea

@Dao
interface AnimeDao {

    @Query("SELECT * FROM AnimeIdea")
    suspend fun getAnimeIdeas(): List<AnimeIdea>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeIdea(animeIdea: AnimeIdea): Long
}