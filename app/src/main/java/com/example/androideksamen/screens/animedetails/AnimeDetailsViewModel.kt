package com.example.androideksamen.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.api.AnimeAPIRepository
import com.example.androideksamen.data.dataclasses.anime.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//TRENGER VI EN EGEN VIEWMODEL HER? DETAILSSCREEN KAN JO BARE HENTE FRA ANIMEVM
// --UMAIR
// TROR DEN MÅ HA EGEN? HAN GJORDE DET I FORELESNING.
// MEN DA HADDE VI OGSÅ BARE EN SCREEN -- JEANETTE
class AnimeDetailsViewModel : ViewModel() {
    private val _anime = MutableStateFlow<Anime?>(null)
    val anime = _anime.asStateFlow()

    fun setAnime(animeId: Int) {
        viewModelScope.launch {
            _anime.value = AnimeAPIRepository.getAnimeById(animeId)
        }
    }
}