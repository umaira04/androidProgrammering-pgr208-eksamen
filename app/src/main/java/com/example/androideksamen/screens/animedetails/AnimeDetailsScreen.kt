package com.example.androideksamen.screens.animedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androideksamen.components.items.AnimeDetailsItem

@Composable
fun AnimeDetailsScreen(
    animeDetailsViewModel: AnimeDetailsViewModel,
    navController: NavController,
    animeId: Int,
) {
    val anime = animeDetailsViewModel.anime.collectAsState()

    LaunchedEffect(Unit) {
        animeDetailsViewModel.setAnime(animeId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBBAED))
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {

        IconButton( // TODO: DENNE MÅ STYLES SÅ ALLE KNAPPENE VÅRE ER LIKE
            // Go back button
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(start = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .width(60.dp)
                .height(48.dp)

        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
        }// End go back button

        anime.value?.let {
            AnimeDetailsItem(
                it
            )
        }

    }
}// End AnimeDetailsScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimeDetailsScreenPreview() {
    AnimeDetailsScreen(
        animeDetailsViewModel = AnimeDetailsViewModel(),
        navController = rememberNavController(),
        animeId = 1
    )
}
