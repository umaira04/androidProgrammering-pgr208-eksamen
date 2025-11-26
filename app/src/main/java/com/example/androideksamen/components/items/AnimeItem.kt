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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.anime.Genre

@Composable
fun AnimeItem(
    anime: Anime,
    showDetails: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable { showDetails() }
            .background(
                color = Color(0xFFF7EAF9),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text( // English
                text = anime.titleEnglish?.toString() ?: "No english title",
                fontSize = if (anime.titleEnglish.toString().length > 20) 24.sp else 32.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0A0E0D)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box( // Main box
                modifier = Modifier
                    .fillMaxWidth() //TODO: endre dette
            ) {
                AsyncImage(
                    model = anime.images?.jpg?.imageUrl,
                    contentDescription = "bilde av ${anime.titleEnglish}",
                    modifier = Modifier
                        .width(270.dp)
                        .height(402.dp)
                )
                Text( // Japanese
                    text = anime.titleJapanese.toString(),
                    fontSize = if (anime.titleJapanese.toString().length > 12) 24.sp else 32.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 32.sp,
                    color = Color(0xFF0A0E0D),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = 144.dp) // TODO: si at det er AI?
                        .rotate(90f)
                )
            } // End main box

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = anime.genres?.firstOrNull()?.name ?: "No genre",
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF0A0E0D),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = anime.year?.toString() ?: "No year",
                    fontSize = 24.sp,
                    maxLines = 1,
                    color = Color(0xFF0A0E0D),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnimeItemPreview() {
    AnimeItem(
        anime = Anime(
            id = 1,
            titleEnglish = "Pokemon",
            genres = listOf(Genre(name = "Action")),
            year = 1997,
            titleJapanese = "ポケモン"
        ),
        showDetails = {}
    )
}