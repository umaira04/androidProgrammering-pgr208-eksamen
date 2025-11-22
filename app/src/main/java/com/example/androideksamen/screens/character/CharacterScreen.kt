package com.example.androideksamen.screens.character

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.CharacterList
import com.example.androideksamen.data.dataclasses.character.Character

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel
) {
    val focusManager = LocalFocusManager.current
    var searchQuery by remember { mutableStateOf("") }

    val mockCharacters = listOf(
        Character(1, name = "Pikachu", characterImage = null, nameJapanese = "Japansk Pikachu"),
        Character(2, name = "Charizard", characterImage = null, nameJapanese = "Japansk Pikachu"),
        Character(3, name = "Bulbasaur", characterImage = null, nameJapanese = "Japansk Pikachu",),
        Character(4, name = "Venusaur", characterImage = null, nameJapanese = "Japansk Pikachu"),
        Character(5, name = "Groudon", characterImage = null, nameJapanese = "Japansk Pikachu",),
        Character(6, name = "Dragonite", characterImage = null, nameJapanese = "Japansk Pikachu",),
        Character(7, name = "Lucario", characterImage = null, nameJapanese = "Japansk Pikachu",),
        Character(8, name = "Pichu", characterImage = null, nameJapanese = "Japansk Pikachu",)
    )
    val characters by characterViewModel.characters.collectAsState()

    val filteredCharacters = mockCharacters.filter { character ->
        character.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Tittel
        Text(
            text = "Character List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

        // CharacterList
        CharacterList(
            characterList = characters,
            modifier = Modifier.weight(1f)
        )

    } // End column
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun CharacterScreenPreview() {
    CharacterScreen(
        characterViewModel = CharacterViewModel()
    )
}
