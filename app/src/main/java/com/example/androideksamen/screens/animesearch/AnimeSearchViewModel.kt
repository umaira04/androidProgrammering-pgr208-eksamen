package com.example.androideksamen.screens.animesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.api.AnimeAPIRepository
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.character.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeSearchViewModel : ViewModel() {

    //FORELESNING 9 FOR Å SE AKKURAT DENNE OPPGAVEN
    private val _anime = MutableStateFlow<Anime?>(null)
    val anime = _anime.asStateFlow()

    private val _mainCharacters = MutableStateFlow<List<Character>>(emptyList())
    val mainCharacters = _mainCharacters.asStateFlow()

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    private val _isSearched = MutableStateFlow(false)
    val isSearched = _isSearched.asStateFlow()

    fun setId(value: String) { _id.value = value }

    fun searchAnime() {
        val idParsed = _id.value.toIntOrNull()
        if (idParsed != null) {
            setAnimeById(idParsed)
            setAnimeMainCharactersByAnimeId(idParsed)
            _isSearched.value = true
        }
    }

    fun setAnimeById(id: Int) {
        viewModelScope.launch {
            _anime.value = AnimeAPIRepository.getAnimeById(id)
        }
    }

    fun setAnimeMainCharactersByAnimeId(id: Int) {
        viewModelScope.launch {
            _mainCharacters.value = AnimeAPIRepository.getAllMainCharacters(id)
        }
    }

}