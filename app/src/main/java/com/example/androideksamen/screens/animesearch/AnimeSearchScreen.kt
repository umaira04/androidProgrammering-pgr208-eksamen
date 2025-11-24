package com.example.androideksamen.screens.animesearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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




    Column(
        modifier = Modifier
            .background(color = Color(0xFFFBBAED))
            .fillMaxSize()
    ) {
        Text(
            "Search for animes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A0E0D),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        //TODO LEGGE INN ANNEN TYPE TEKSTBOKS SÅ MAN KAN TRYKKE ENTER PÅ TASTATUR?
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color(0Xfff6f6f6)),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("Id") }
            )
            OutlinedButton(
                onClick = {
                    val idParsed = id.toIntOrNull()
                    if (idParsed != null) {
                        animeSearchViewModel.setAnimeById(idParsed)
                        animeSearchViewModel.setAnimeMainCharactersByAnimeId(idParsed)
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Søk")
            }

        }


        anime?.let { anime ->
            AnimeDetailsItem(
                anime = anime,
                goBack = {},
                isSearchScreen = true,
                characters = mainCharacters
            )
        } ?: Text("Search for an anime by ID")


    }
}
