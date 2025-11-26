package com.example.androideksamen.components.lists

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androideksamen.components.items.CharacterItem
import com.example.androideksamen.data.dataclasses.character.Character

@Composable
fun CharacterList(
    characterList: List<Character>,
    favorites: List<Int>,
    onFavoriteClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(characterList) { character ->
            CharacterItem(
                character = character,
                isFavorite = favorites.contains(character.id),
                onFavClick = { onFavoriteClick(character.id) }
            )
        }
    }
}