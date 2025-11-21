package com.example.androideksamen.screens.character

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

    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = AnimeAPIRepository.getAllCharacters()
        }
    }

    init {
        setCharacters()
    }

}