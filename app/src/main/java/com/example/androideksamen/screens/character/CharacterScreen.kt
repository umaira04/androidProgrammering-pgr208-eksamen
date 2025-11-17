package com.example.androideksamen.screens.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.CharacterList
import com.example.androideksamen.data.api.Character

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel
) {
    val mockCharacters = listOf(
        Character(1, "Pikachu"),
        Character(2, "Charizard"),
        Character(3, "Bulbasaur"),
        Character(4, "Venusaur"),
        Character(5, "Groudon"),
        Character(6, "Dragonite"),
        Character(7, "Lucario"),
        Character(8, "Pichu")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Character List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

        CharacterList(
            characterList = mockCharacters
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterScreenPreview() {
    CharacterScreen(
        characterViewModel = CharacterViewModel()
    )
}
