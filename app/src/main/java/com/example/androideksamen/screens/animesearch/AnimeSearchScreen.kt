package com.example.androideksamen.screens.animesearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.util.TableInfo
import com.example.androideksamen.components.items.AnimeDetailsItem


//FORELESNING 9 FOR Å SE AKKURAT DENNE OPPGAVEN
@Composable
fun AnimeSearchScreen(
    animeSearchViewModel: AnimeSearchViewModel,
    navController: NavController
) {
    val anime by animeSearchViewModel.anime.collectAsState()
    val mainCharacters by animeSearchViewModel.mainCharacters.collectAsState()
    var id by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(color = Color(0xFFFBBAED))
            .fillMaxSize()
    ) {
        // Tittel
        Text(
            "Search for animes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A0E0D),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ){
            anime?.let { anime ->
                AnimeDetailsItem(
                    anime = anime,
                    goBack = {},
                    isSearchScreen = true,
                    characters = mainCharacters
                )
            } ?: Text("")
        }

        Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = id,
                onValueChange = { id = it },
                placeholder = { Text("Search by ID") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(
                    onGo = {
                        val idParsed = id.toIntOrNull()
                        if (idParsed != null) {
                            animeSearchViewModel.setAnimeById(idParsed)
                            animeSearchViewModel.setAnimeMainCharactersByAnimeId(idParsed)
                        }
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF656391),
                    unfocusedContainerColor = Color(0xFF656391),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = Color(0xFFF7EAF9),
                    unfocusedPlaceholderColor = Color(0xFFF7EAF9),
                    focusedTextColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF)
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeSearchScreenPreview() {
    AnimeSearchScreen(
        animeSearchViewModel = AnimeSearchViewModel(),
        navController = rememberNavController()
    )
}
