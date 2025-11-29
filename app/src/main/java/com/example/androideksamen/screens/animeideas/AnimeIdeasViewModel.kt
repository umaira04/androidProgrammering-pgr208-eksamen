package com.example.androideksamen.screens.animeideas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.database.AnimeDB
import com.example.androideksamen.data.database.AnimeDbRepository
import com.example.androideksamen.data.database.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeIdeasViewModel : ViewModel() {

    // Instansvariabler
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

    private val _isDeleting = MutableStateFlow(false)
    val isDeleting = _isDeleting.asStateFlow()

    private val _animeIdeaToDelete = MutableStateFlow<AnimeDB?>(null)
    val animeIdeaToDelete = _animeIdeaToDelete.asStateFlow()

    private val _userFeedbackMessage = MutableStateFlow("")
    val userFeedbackMessage = _userFeedbackMessage.asStateFlow()


    // Funksjoner som kjører når appen starter
    init {
        setAnimeIdeas()
    }

    // Ved første bruk av appen så vil ikke setAnimeIdeas returnere noe da db er tom

    // Henter verdier fra ROOM db
    fun setAnimeIdeas() {
        viewModelScope.launch(Dispatchers.IO) {
            _animeIdeas.value = AnimeDbRepository.getAnimeIdeas()
        }
    }

    fun insertAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val newAnimeIdeaId = AnimeDbRepository.insertAnimeIdeas(animeIdea)
            if (newAnimeIdeaId != -1L) {
                val newAnimeIdea = animeIdea.copy(id = newAnimeIdeaId.toInt())
                _animeIdeas.value += newAnimeIdea
            } else {
                Log.d(
                    "insertAnimeIdeaElse", "error inserting new animeIdea from AnimeIdeasViewModel"
                )
            }
        }
    }

    // Sletter animeIde fra DB
    fun deleteAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val deletedRows = AnimeDbRepository.deleteAnimeIdea(animeIdea)
            if (deletedRows > 0) {
                _animeIdeas.value -= animeIdea
            } else {
                Log.d(
                    "deleteAnimeIdeaElse", "Error deleting animeIdea from AnimeIdeasViewModel"
                )
            }
        }
    }

    // Oppdaterer animeIde i db
    fun updateAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedRows = AnimeDbRepository.updateAnimeIdea(animeIdea)
            Log.d("animeIdea updatedRows", updatedRows.toString())
            if (updatedRows > 0) {
                setAnimeIdeas()
                Log.d("animeIdeaState updated", _animeIdeas.toString())
            } else {
                Log.d(
                    "UpdateAnimeIdeaElse", "Error updating animeIdea from AnimeIdeasViewModel"
                )
            }
        }
    }

    // STØTTEFUNKSJONER

    // REDIGERING AV IDE
    fun handleEditBtnClick(animeIdea: AnimeDB) {
        _title.value = animeIdea.title
        _synopsis.value = animeIdea.synopsis
        _id.value = animeIdea.id
        _genre.value = animeIdea.genre
        _isEditing.value = true
    }

    // Angre redigering
    fun cancelEditing() {
        _isEditing.value = false
        clearForm()
    }

    // SLETTING AV IDE
    fun handleDeleteBtnClick(animeIdea: AnimeDB) {
        _isDeleting.value = true
        _animeIdeaToDelete.value = animeIdea
    }

    // Angre sletting av ide
    fun cancelDelete() {
        _isDeleting.value = false
        _animeIdeaToDelete.value = null
    }

    fun confirmDelete() {
        _animeIdeaToDelete.value?.let { animeIdea ->
            deleteAnimeIdea(animeIdea)
        }
        _isDeleting.value = false
        _animeIdeaToDelete.value = null
        showFeedbackMessage("Delete: Successful")
    }

    // FEEDBACK
    // Gir bruker feedback ved sletting og/eller redigering
    fun showFeedbackMessage(message: String) {
        viewModelScope.launch {
            _userFeedbackMessage.value = message
            delay(3000)
            _userFeedbackMessage.value = ""
        }
    }

    // Setter tittel
    fun setTitle(value: String) {
        _title.value = value
    }

    // Setter synopsis
    fun setSynopsis(value: String) {
        _synopsis.value = value
    }

    // Setter sjanger
    fun setGenre(value: Genre) {
        _genre.value = value
    }

    // Tømmer verdiene fra inputfelt.
    // Kjøres etter lagring eller kansellering
    fun clearForm() {
        _title.value = ""
        _synopsis.value = ""
        _genre.value = Genre.OTHER
    }
}