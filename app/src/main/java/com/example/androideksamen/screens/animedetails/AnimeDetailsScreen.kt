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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androideksamen.components.items.AnimeDetailsItem
import com.example.androideksamen.components.shared.DarkBlue
import com.example.androideksamen.components.shared.DarkPink

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
            .background(DarkPink)
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {

        IconButton( // TODO: DENNE MÅ STYLES SÅ ALLE KNAPPENE VÅRE ER LIKE
            // Go back button
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(start = 24.dp, top = 8.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .width(60.dp)
                .height(48.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Go back",
                tint = DarkBlue
            )
        }// End go back button

        anime.value?.let {
            AnimeDetailsItem(
                it
            )
        }
    }
}// End AnimeDetailsScreen
