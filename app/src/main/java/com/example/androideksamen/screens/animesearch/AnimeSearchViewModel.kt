package com.example.androideksamen.screens.animesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.api.AnimeAPIRepository
import com.example.androideksamen.data.dataclasses.anime.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeSearchViewModel : ViewModel() {

    private val _anime = MutableStateFlow<Anime?>(null)

    val anime = _anime.asStateFlow()


    fun setAnimeById(id: Int) {
        viewModelScope.launch {
            _anime.value = AnimeAPIRepository.getAnimeById(id)
        }
    }

}