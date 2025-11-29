package com.example.androideksamen.screens.anime

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androideksamen.components.lists.AnimeList
import com.example.androideksamen.components.shared.DarkPink
import com.example.androideksamen.components.shared.ErrorLoading
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.navigation.NavRoutes

//TODO: HJERTEKNAPP TIL Å VELGE FAVORITTER
//TODO: FILTER PÅ SJANGER
@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {

    val animes by animeViewModel.animes.collectAsState()

    Column(
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

        // Vis favoritter

    } // End column
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AnimeScreenPreview() {
    AnimeScreen(
        animeViewModel = AnimeViewModel(),
        navController = rememberNavController()
    )
}