package com.example.androideksamen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

// AppDatabase konfigurerer databasen
@Database(
    entities = [AnimeIdea::class],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}