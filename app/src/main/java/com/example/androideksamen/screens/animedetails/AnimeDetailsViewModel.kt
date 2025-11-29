package com.example.androideksamen.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.api.AnimeAPIRepository
import com.example.androideksamen.data.dataclasses.anime.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel : ViewModel() {

    // Instansvariabler
    private val _anime = MutableStateFlow<Anime?>(null)
    val anime = _anime.asStateFlow()

    // Henter verdier fra API
    fun setAnime(animeId: Int) {
        viewModelScope.launch {
            _anime.value = AnimeAPIRepository.getAnimeById(animeId)
        }
    }
}