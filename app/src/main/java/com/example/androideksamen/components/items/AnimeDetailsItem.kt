package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androideksamen.data.dataclasses.anime.Anime

@Composable
fun AnimeDetailsItem(
    anime: Anime,
    goBack: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFBBAED),
            )
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {

        item { // TO DO: DENNE MÅ STYLES SÅ ALLE KNAPPENE VÅRE ER LIKE
            Button(
                // Go back button
                onClick = { goBack() },
            ) {
                Text("Go back")
            }
        } // End go back button


        item { // Image
            AsyncImage(
                model = anime.images?.jpg?.imageUrl,
                contentDescription = "bilde av ${anime.titleEnglish}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .padding(bottom = 8.dp)
            )
        }// End image

        item { // Start row with title and rating box
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text( // Title english
                        text = anime.titleEnglish ?: "No english title",
                        fontSize = if (anime.titleEnglish.toString().length > 20) 24.sp else 32.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0A0E0D)
                    )
                    Text( // Title japanese
                        text = anime.titleJapanese ?: "No japanese title",
                        fontSize = if (anime.titleJapanese.toString().length > 12) 16.sp else 24.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 24.sp,
                        color = Color(0xFF0A0E0D)
                    )
                }

                Box(  // Yellow rating box
                    modifier = Modifier
                        .size(80.dp, 32.dp)
                        .background(
                            color = Color(0xFFfdf1b2),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Filled.Star, contentDescription = "none")
                        Text(
                            text = anime.score?.toString() ?: "...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                } // End yellow rating box
            } // End row with title and rating box
        }

        item {  // Row with anime info: Year, Episodes, Type
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Year",
                        color = Color(0xFF656391),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.year?.toString() ?: "No year",
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Type",
                        color = Color(0xFF656391),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.type ?: "No type",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }

                if (anime.type != null) {
                    if (anime.type == "TV") {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Episodes",
                                color = Color(0xFF656391),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = anime.episodes?.toString() ?: "No episodes",
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                        }
                    } else { // End if anime.type == TV
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Duration",
                                color = Color(0xFF656391),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = anime.duration ?: "No duration",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                )
                            )
                        }
                    } // End else if anime.type != TV
                } // End if anime.type != null

            }
        }// End row with anime info: Year, Episodes, Type

        item {  // Synposis
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Synopsis ",
                    color = Color(0xFF656391),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    anime.synopsis ?: "No synopsis",
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        } // End synopsis

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Genres ",
                    color = Color(0xFF656391),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row( // Start row genres
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    anime.genres?.forEach { genre ->
                        Box(
                            modifier = Modifier
                                .size(80.dp, 32.dp)
                                .background(
                                    color = Color(0xFF979ffb),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = genre.name.toString(),
                                fontSize = 16.sp,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )
                        }
                    }
                }
            } // End row genres
        }

        item {
            Text(
                // More infomation
                text = "More information",
                color = Color(0xFF656391),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(  // URL
                text = anime.url.toString(), // TO DO: GJØRE DENNE KLIKKBAR
                color = Color.Black,
                fontSize = 16.sp
            )
        } // End more information
    } // End Main LazyColumn
} // End AnimeDetailsItem


