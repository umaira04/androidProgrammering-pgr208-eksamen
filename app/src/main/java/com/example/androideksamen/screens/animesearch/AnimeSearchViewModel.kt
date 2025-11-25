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
    private val _mainCharacters = MutableStateFlow<List<Character>>(emptyList())
    val anime = _anime.asStateFlow()
    val mainCharacters = _mainCharacters.asStateFlow()


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