package com.example.androideksamen.screens.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.androideksamen.data.api.Character

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel
) {
    val focusManager = LocalFocusManager.current
    var inputText by remember { mutableStateOf("") }

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

        // Tittel
        Text(
            text = "Character List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

        // CharacterList
        CharacterList(
            characterList = mockCharacters,
            modifier = Modifier.weight(1f)
        )

        // Søkefelt
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Søk etter karakter") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            colors = TextFieldDefaults.colors(
                //focusedContainerColor = Color(),
                //unfocusedContainerColor = Color(),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
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
