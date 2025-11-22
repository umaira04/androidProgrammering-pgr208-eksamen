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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.anime.Genre

@Composable
fun AnimeItem(
    anime: Anime,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    showDetails: () -> Unit
) {
    Box(
        modifier = Modifier
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
                Row( // Tittel med hjerte
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {



                Text( // English
                    text = anime.titleEnglish.toString(),
                    fontSize = if (anime.titleEnglish.toString().length > 20) 24.sp else 32.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A0E0D)
                )

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove" else "Favorite",
                        tint = if (isFavorite) Color(0xFF324663) else Color(0xFF324663),
                        modifier = Modifier.size(32.dp)
                        )
                    }
                } // end row

                Spacer(modifier = Modifier.height(16.dp))

                Box( // Main box
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box( // Image (skal byttes ut med bilde)
                        modifier = Modifier
                            .width(270.dp)
                            .height(402.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Magenta)
                    )
                    Text( // Japanese
                        text = anime.titleJapanese.toString(),
                        fontSize = if (anime.titleJapanese.toString().length > 12) 24.sp else 32.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 32.sp,
                        color = Color(0xFF0A0E0D),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = 144.dp)
                            .rotate(90f)
                    )
                } // End main box

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = anime.genres?.firstOrNull()?.name ?: "",
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF0A0E0D)
                    )
                    Text(
                        text = anime.year?.toString() ?: "",
                        fontSize = 24.sp,
                        maxLines = 1,
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
            titleJapanese = "ポケモン"
        ),
        isFavorite =  false,
        onFavoriteClick = {},
        showDetails = {}
    )
}