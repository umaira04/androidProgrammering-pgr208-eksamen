package com.example.androideksamen.screens.animesearch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.androideksamen.components.items.AnimeItem
import com.example.androideksamen.navigation.NavRoutes

@Composable
fun AnimeSearchScreen(
    animeSearchViewModel: AnimeSearchViewModel,
    navController: NavController
) {

    val anime by animeSearchViewModel.anime.collectAsState()
    var id by remember { mutableStateOf("") }




    Column() {
        Text("AnimeSearchScreen")



        Column {
            Text("pokemon søk")

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
                    }
                }
            ) {
                Text("Søk")
            }

            anime?.let { anime ->
                AnimeItem(anime = anime,
                    showDetails = {})

            } ?: Text("Søk for å vise anime")

            Text(anime.toString())
        }
    }
}