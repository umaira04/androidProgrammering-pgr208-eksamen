package com.example.androideksamen.screens.anime

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {

    Column() {
        Text("AnimeScreen")
    }
}