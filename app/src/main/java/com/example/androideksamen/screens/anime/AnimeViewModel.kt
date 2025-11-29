package com.example.androideksamen.screens.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.api.AnimeAPIRepository
import com.example.androideksamen.data.dataclasses.anime.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {

    // Instansvariabler
    private val _animes = MutableStateFlow<List<Anime>>(emptyList())
    val animes = _animes.asStateFlow()

    // Funksjoner som kjører når appen starter
    init {
        setAnimes()
    }

    // Henter verdier fra API
    fun setAnimes() {
        viewModelScope.launch(Dispatchers.IO) {
            _animes.value = AnimeAPIRepository.getAllAnime()
        }
    }
}