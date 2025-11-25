package com.example.androideksamen.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_idea")
    suspend fun getAnimeIdeas(): List<AnimeDB>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeIdea(animeIdea: AnimeDB): Long

    @Update
    suspend fun updateAnimeIdea(animeIdea: AnimeDB) : Int

    @Delete
    suspend fun deleteAnimeIdea(animeIdea: AnimeDB) : Int

    @Query("SELECT * FROM anime_idea WHERE id = :id")
    suspend fun getAnimeIdeaById(id: Int): AnimeDB?
}
