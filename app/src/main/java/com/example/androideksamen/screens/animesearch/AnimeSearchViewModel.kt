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

    // Instansvariabler
    private val _anime = MutableStateFlow<Anime?>(null)
    val anime = _anime.asStateFlow()

    private val _mainCharacters = MutableStateFlow<List<Character>>(emptyList())
    val mainCharacters = _mainCharacters.asStateFlow()

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    private val _isSearched = MutableStateFlow(false)
    val isSearched = _isSearched.asStateFlow()

    // Søker og henter anime og hovedkarakterer fra API
    fun searchAnime() {
        val idParsed = _id.value.toIntOrNull()
        if (idParsed != null) {
            setAnimeById(idParsed)
            setAnimeMainCharactersByAnimeId(idParsed)
            _isSearched.value = true
        }
    }

    // Henter anime fra API basert på id
    fun setAnimeById(id: Int) {
        viewModelScope.launch {
            _anime.value = AnimeAPIRepository.getAnimeById(id)
        }
    }

    // Henter hovedkarakterer for anime fra API
    fun setAnimeMainCharactersByAnimeId(id: Int) {
        viewModelScope.launch {
            _mainCharacters.value = AnimeAPIRepository.getAllMainCharacters(id)
        }
    }

    // Setter id
    fun setId(value: String) {
        _id.value = value
    }

}