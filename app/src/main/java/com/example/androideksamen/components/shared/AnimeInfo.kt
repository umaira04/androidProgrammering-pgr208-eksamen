package com.example.androideksamen.components.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


// Viser fremhevet informasjon om en Anime i AnimeDetailsScreen (Tittel og info sentrert under hverandre)
// Laget komponent for å minimere gjentagende kode da denne ble brukt fire ganger etter hverandre

@Composable
fun AnimeInfo(
    subtitle: String,
    animeInfo: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Subtitle(subtitle)
        BodyText(animeInfo)
    }
}