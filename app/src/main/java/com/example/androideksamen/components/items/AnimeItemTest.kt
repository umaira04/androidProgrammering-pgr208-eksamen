package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.anime.Genre

@Composable
fun AnimeItemTest(
    anime: Anime, showDetails: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Main padding for the entire card
            .clickable { showDetails() }
            .background(Color(0xFFFBBAED), RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Inner padding for the content
        ) {
            // English Title
            Text(
                text = anime.titleEnglish ?: "No Title",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // This Box will contain the image and the rotated Japanese title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(402.dp) // Set a fixed height for the content area
            ) {
                // Magenta Image Box
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart) // Align to the top-left of the parent Box
                        .width(270.dp)
                        .height(402.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Magenta)
                )

                // Rotated Japanese Title
                Text(
                    text = anime.titleJapanese ?: "",
                    fontSize = 24.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center) // Align to the center of the parent Box first
                        .offset(x = 160.dp) // Nudge it horizontally to the right
                        .graphicsLayer(rotationZ = 90f) // Rotate it
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Genres and Year at the bottom
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = anime.genres?.take(3)?.joinToString(", ") { it.name ?: "Unknown" }
                        ?: "No genres",
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Text(
                    text = anime.year?.toString() ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 650)
@Composable
fun AnimeItemTestPreview() {
    AnimeItemTest(
        anime = Anime(
            id = 1,
            titleEnglish = "Cowboy Bebop",
            genres = listOf(Genre(name = "Action"), Genre(name = "Drama"), Genre(name = "Sci-Fi")),
            year = 1998,
            titleJapanese = "カウボーイビバップ" // "Kaubōi Bibappu"
        ), showDetails = {}
    )
}