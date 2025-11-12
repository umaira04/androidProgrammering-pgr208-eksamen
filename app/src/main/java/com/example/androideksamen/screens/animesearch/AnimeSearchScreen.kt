package com.example.androideksamen.screens.animesearch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AnimeSearchScreen(
    animeSearchViewModel: AnimeSearchViewModel,
    navController: NavController
) {
    Column() {
        Text("AnimeSearchScreen")
    }
}