package com.example.androideksamen.screens.anime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androideksamen.components.lists.AnimeList
import com.example.androideksamen.components.shared.DarkPink
import com.example.androideksamen.components.shared.ErrorLoading
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.navigation.NavRoutes

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {

    // State fra ViewModel
    val animes by animeViewModel.animes.collectAsState()

    Column( // Main column start
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPink)
            .padding(16.dp, 8.dp, 16.dp, 0.dp)
    ) {
        // Tittel
        Title("Anime")

        // AnimeList
        if (animes.isEmpty()) {
            ErrorLoading(1, "anime")
        } else {
            AnimeList(
                animeList = animes,
                onAnimeClicked = { animeId ->
                    navController.navigate(
                        NavRoutes.AnimeDetailsRoute(animeId)
                    )
                }
            )
        }
    } // End main column
}