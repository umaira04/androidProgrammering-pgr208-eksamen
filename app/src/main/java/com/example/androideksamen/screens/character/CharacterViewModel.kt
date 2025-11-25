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

    private val _favorites = MutableStateFlow<List<String>>(emptyList())
    val favorites = _favorites.asStateFlow()

    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = AnimeAPIRepository.getAllCharacters()
        }
    }

    init {
        setCharacters()
    }

    fun toggleFavorite(characterId: Int) {
        val currentFavorites = _favorites.value.toMutableList()
        val idString = characterId.toString()

        if (currentFavorites.contains(idString)) {
            currentFavorites.remove(idString)
            Log.d("CharacterViewModel", "Fjernet favoritt: $idString")
        } else {
            currentFavorites.add(idString)
            Log.d("CharacterViewModel", "La til favoritt: $idString")
        }
        _favorites.value = currentFavorites
    }

}