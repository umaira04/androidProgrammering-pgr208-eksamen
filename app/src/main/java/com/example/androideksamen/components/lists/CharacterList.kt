package com.example.androideksamen.components.lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androideksamen.components.items.CharacterItem
import com.example.androideksamen.data.dataclasses.character.Character

@Composable
fun CharacterList(
    characterList: List<Character>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier)
    {
        items(characterList){ character ->
            CharacterItem(character = character)
        }
    }
}