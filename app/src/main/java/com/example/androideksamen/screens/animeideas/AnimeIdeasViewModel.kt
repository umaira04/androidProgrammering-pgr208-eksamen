package com.example.androideksamen.screens.animeideas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.database.AnimeDB
import com.example.androideksamen.data.database.AnimeDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeIdeasViewModel : ViewModel() {

    private val _animeIdeas = MutableStateFlow<List<AnimeDB>>(emptyList())
    val animeIdeas = _animeIdeas.asStateFlow()

    fun setAnimeIdeas() {
        viewModelScope.launch(Dispatchers.IO) {
            _animeIdeas.value = AnimeDbRepository.getAnimeIdeas()
        }
    }

    init {
        setAnimeIdeas()
    }

    fun insertAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val newAnimeIdeaId = AnimeDbRepository.insertAnimeIdeas(animeIdea)
            if (newAnimeIdeaId != -1L) {
                val newAnimeIdea = animeIdea.copy(id = newAnimeIdeaId.toInt())
                _animeIdeas.value += newAnimeIdea
            } else {
                //TODO LEGG INN KODE FOR Å SI IFRA TIL BRUKER AT NOE HAR GÅTT GALT
                Log.d(
                    "insertAnimeIdeaElse", "error inserting new animeIdea from AnimeIdeasViewModel"
                )
            }
        }
    }

    fun deleteAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val deletedRows = AnimeDbRepository.deleteAnimeIdea(animeIdea)
            if (deletedRows > 0) {
                _animeIdeas.value -= animeIdea
            } else {
                //TODO  LEGG INN KODE FOR Å SI IFRA TIL BRUKER AT NOE HAR GÅTT GALT
                Log.d(
                    "deleteAnimeIdeaElse", "error deleting animeIdea from AnimeIdeasViewModel"
                )
            }

        }
    }

    fun updateAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedRows = AnimeDbRepository.updateAnimeIdea(animeIdea)
            Log.d("animeIdea updatedRows", updatedRows.toString())
            if (updatedRows > 0) {
                setAnimeIdeas()
                Log.d("animeIdeaState updated", _animeIdeas.toString())
            } else {
                //TODO LEGGE INN KODE FOR Å SI IFRA TIL BRUKER AT NOE HAR GÅTT GALT
                Log.d(
                    "updateAnimeIdeaElse", "error updating animeIdea from AnimeIdeasViewModel"
                )
            }
        }
    }


}