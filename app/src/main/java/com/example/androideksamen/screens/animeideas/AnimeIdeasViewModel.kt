package com.example.androideksamen.screens.animeideas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.database.AnimeDB
import com.example.androideksamen.data.database.AnimeDbRepository
import com.example.androideksamen.data.database.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class AnimeIdeasViewModel : ViewModel() {

    private val _animeIdeas = MutableStateFlow<List<AnimeDB>>(emptyList())
    val animeIdeas = _animeIdeas.asStateFlow()
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _synopsis = MutableStateFlow("")
    val synopsis = _synopsis.asStateFlow()

    private val _id = MutableStateFlow(0)
    val id = _id.asStateFlow()

    private val _genre = MutableStateFlow(Genre.OTHER)
    val genre = _genre.asStateFlow()

    private val _isEditing = MutableStateFlow(false)
    val isEditing = _isEditing.asStateFlow()

    private val _userFeedbackMessage = MutableStateFlow("")
    val userFeedbackMessage = _userFeedbackMessage.asStateFlow()

    fun setTitle(value: String) { _title.value = value }
    fun setSynopsis(value: String) { _synopsis.value = value }
    fun setGenre(value: Genre) { _genre.value = value }

    fun showFeedbackMessage(message: String) {
        viewModelScope.launch {
            _userFeedbackMessage.value = message
            delay(3000)
            _userFeedbackMessage.value = ""
        }
    }

    fun handleEditBtnClick(animeIdea: AnimeDB) {
        _title.value = animeIdea.title
        _synopsis.value = animeIdea.synopsis
        _id.value = animeIdea.id
        _genre.value = animeIdea.genre
        _isEditing.value = true
    }

    fun cancelEditing() {
        _isEditing.value = false
        clearForm()
    }

    fun clearForm() {
        _title.value = ""
        _synopsis.value = ""
        _genre.value = Genre.OTHER
    }

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