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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.anime.Genre

@Composable
fun AnimeItem(
    anime: Anime,
    showDetails: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            // .height(576.dp)
            .padding(8.dp)
            .clickable{showDetails()}
            .background(
                color = Color(0xFFFBBAED),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = anime.titleEnglish.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A0E0D)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row() {
                    Box(
                        modifier = Modifier
                            .width(270.dp)
                            .height(402.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Magenta)
                    )
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(402.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = anime.titleJapanese.toString(),
                            fontSize = 24.sp,
                            maxLines = 1,
                            color = Color(0xFF0A0E0D),
                            modifier = Modifier
                                .rotate(90f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = anime.genres?.take(2)?.joinToString(", ") { it.name ?: "Unknown"  } ?: "No genres",
                        fontSize = 24.sp,
                        color = Color(0xFF0A0E0D)
                    )

                    Text(
                        text = anime.year.toString(),
                        fontSize = 24.sp,
                        color = Color(0xFF0A0E0D)
                    )
                }
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