package com.example.androideksamen.screens.character

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.CharacterList
import com.example.androideksamen.components.shared.DarkBlue
import com.example.androideksamen.components.shared.ErrorLoading
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.fonts.BodyFont

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
            .background(Color(0xFFFBBAED))
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {
        // Tittel
        Title("Characters")

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // CharacterList
            if (characters.isEmpty()) {
                ErrorLoading(1, "character")

            } else {
                if (showOnlyFavorites && favorites.isEmpty()) {
                    Text(
                        text = "No favorites. Press the heart to mark a character as favorite",
                        fontSize = 16.sp,
                        fontFamily = BodyFont,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {

                    CharacterList(
                        characterList = displayedCharacters,
                        favorites = favorites,
                        onFavoriteClick = { characterId ->
                            characterViewModel.toggleFavorite(characterId)
                        },
                    )
                }
            }

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
