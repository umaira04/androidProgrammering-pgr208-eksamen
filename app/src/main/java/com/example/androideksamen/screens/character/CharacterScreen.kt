package com.example.androideksamen.screens.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.CharacterList
import com.example.androideksamen.components.shared.DarkBlue
import com.example.androideksamen.components.shared.DarkPink
import com.example.androideksamen.components.shared.ErrorLoading
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.fonts.BodyFont

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel
) {

    // State fra ViewModel
    val characters by characterViewModel.characters.collectAsState()
    val favorites by characterViewModel.favorites.collectAsState()

    var showOnlyFavorites: Boolean by remember { mutableStateOf(false) }

    // Filtrerer karakterer basert på favoritt
    val displayedCharacters = if (showOnlyFavorites) {
        characters.filter { favorites.contains(it.id) }
    } else {
        characters
    }

    Column( // Start main column
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPink)
            .padding(16.dp, 8.dp, 16.dp, 0.dp)
    ) {
        // Tittel
        Title("Characters")

        Box( // Start box med alt innhold for skjermen
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // CharacterList
            if (characters.isEmpty()) {
                // Feilmelding hvis ingen karakterer er lastet
                ErrorLoading(1, "character")
            } else {
                if (showOnlyFavorites && favorites.isEmpty()) {
                    // Melding hvis ingen favoritter er valgt
                    Text(
                        text = "No favorites. Press the heart to mark a character as favorite",
                        fontSize = 16.sp,
                        fontFamily = BodyFont,
                        color = Onyx,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {
                    // Viser liste med karakterer
                    CharacterList(
                        characterList = displayedCharacters,
                        favorites = favorites,
                        onFavoriteClick = { characterId ->
                            characterViewModel.toggleFavorite(characterId)
                        }
                    )
                }
            }

            // Knapp for å bytte mellom favoritter og alle karakterer
            Button(
                onClick = { showOnlyFavorites = !showOnlyFavorites },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkBlue,
                    contentColor = Color.White
                )
            ) {
                if (!showOnlyFavorites) {
                    Text(
                        "Show favorites",
                        fontFamily = BodyFont
                    )
                } else {
                    Text(
                        "Show all",
                        fontFamily = BodyFont
                    )
                }
            }
        } // End box
    } // End main column
}
