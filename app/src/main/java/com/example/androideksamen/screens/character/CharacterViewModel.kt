package com.example.androideksamen.screens.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.api.AnimeAPIRepository
import com.example.androideksamen.data.dataclasses.character.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _favorites = MutableStateFlow<List<Int>>(emptyList())
    val favorites = _favorites.asStateFlow()

    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = AnimeAPIRepository.getAllCharacters()
        }
    }

    init {
        setCharacters()
    }

    fun toggleFavorite(charId: Int) {
        val currentFavorites = _favorites.value.toMutableList()

        if (currentFavorites.contains(charId)) {
            currentFavorites.remove(charId)
            Log.d("CharacterViewModel", "Fjernet favoritt: $charId")
        } else {
            currentFavorites.add(charId)
            Log.d("CharacterViewModel", "La til favoritt: $charId")
        }
        _favorites.value = currentFavorites
    }

}