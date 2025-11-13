package com.example.androideksamen.screens.anime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.androideksamen.navigation.NavRoutes

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {
    val animes = animeViewModel.animes.collectAsState()

    Column() {
        Text("AnimeScreen")

        LazyColumn() {
            items(animes.value) { anime ->
                AnimeList(
                    anime,
                    seeDetails = {
                        navController.navigate(
                            NavRoutes.AnimeDetailsRoute(
                                anime.id
                            )
                        )
                    }
                )
            }
        }
    }
}