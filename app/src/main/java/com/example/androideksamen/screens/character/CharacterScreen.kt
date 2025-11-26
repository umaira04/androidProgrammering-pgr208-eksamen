package com.example.androideksamen.screens.character

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.CharacterList

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel
) {

    val characters by characterViewModel.characters.collectAsState()
    val favorites by characterViewModel.favorites.collectAsState()

    var showOnlyFavorites: Boolean by remember { mutableStateOf(false) }

    val displayedCharacters = if (showOnlyFavorites) {
        characters.filter { favorites.contains(it.id) }
    } else {
        characters
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7EAF9))
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {
        // Tittel
        Text(
            text = "Characters",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A0E0D),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // CharacterList
            if (characters.isEmpty()) {
                Text(
                    text = "We're currently having an error loading characters. \nPlease check your network connection and try again",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

            } else {
                if (showOnlyFavorites && favorites.isEmpty()) {
                    Text(
                        text = "No favorites. Press the heart to mark a character as favorite",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {


                    CharacterList(
                        characterList = displayedCharacters,
                        favorites = favorites,
                        onFavoriteClick = { characterId ->
                            characterViewModel.toggleFavorite(characterId)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Button(
                onClick = { showOnlyFavorites = !showOnlyFavorites },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                if (!showOnlyFavorites) {
                    Text("Show favorites")
                } else {
                    Text("Show all")
                }
            }
        }
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
