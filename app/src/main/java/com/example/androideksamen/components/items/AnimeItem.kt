package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.anime.Genre

@Composable
fun AnimeItem(
    anime: Anime,
    showDetails: (() -> Unit)? = null
    //LEGG IN if NOT NULL PÅ SHOW DETAILS. SE SLIDESERIE 20 -U
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{ showDetails?.invoke() }
            .background(
                color = Color(0xFFD0D0D0),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFBBAED))
                .padding(8.dp)


        ) {
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = anime.titleEnglish.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A0E0D)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = anime.genres?.joinToString(",") { it.name ?: "Unknown"  } ?: "No genres",
                    fontSize = 16.sp,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = anime.year.toString(),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box( // bytt ut med bilde
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(120.dp, 80.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeItemPreview(){
    AnimeItem(
        anime = Anime(
            id = 1,
            titleEnglish = "Pokemon",
            genres = listOf(Genre(name = "Action")),
            year = 1997,
            titleJapanese = "Pokemon Japan"
        ),
        showDetails = {}
    )
}