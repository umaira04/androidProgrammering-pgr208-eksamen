package com.example.androideksamen.screens.animedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androideksamen.components.items.AnimeDetailsItem
import com.example.androideksamen.data.api.Anime
import com.example.androideksamen.screens.anime.AnimeViewModel

@Composable
fun AnimeDetailsScreen(
    animeDetailsViewModel: AnimeDetailsViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFfbbaed))
            .padding(8.dp)

    ) {

        // Tilbake knapp

        Button(
            onClick = {},
        ) {
            Text("Back")
        }
        // Tittel
        Text(
            "Anime Details",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, 8.dp)
        )
        AnimeDetailsItem()
    }

} // End AnimeDetailsScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimeDetailsScreenPreview() {
    AnimeDetailsScreen(
        animeDetailsViewModel = AnimeDetailsViewModel(),
        navController = rememberNavController()
    )
}