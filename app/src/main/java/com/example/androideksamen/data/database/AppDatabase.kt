package com.example.androideksamen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androideksamen.data.dataclasses.animeIdea.AnimeIdea

@Database(
    entities = [AnimeDB::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}