package com.example.androideksamen.screens.animeideas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.database.AnimeDB
import com.example.androideksamen.data.database.AnimeDbRepository
import com.example.androideksamen.data.dataclasses.animeIdea.AnimeIdea
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

    fun insertAnimeIdea(animeIdea: AnimeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            val newAnimeIdeaId = AnimeDbRepository.insertAnimeIdeas(animeIdea)
            if (newAnimeIdeaId != -1L) {
                val newAnimeIdea = animeIdea.copy(id = newAnimeIdeaId.toInt())
                _animeIdeas.value += newAnimeIdea
            } else {
                //LEGG INN KODE FOR Å SI IFRA TIL BRUKER AT NOE HAR GÅTT GALT
                Log.d(
                    "insertAnimeIdeaElse", "error inserting new animeIdea from AnimeIdeasViewModel"
                )
            }
        }
    }

    //TODO: SLIDESERIE 19.
    // TODO: LEGG INN FUNKSJONALITET FOR Å GI INPUT I I SCREEN


}