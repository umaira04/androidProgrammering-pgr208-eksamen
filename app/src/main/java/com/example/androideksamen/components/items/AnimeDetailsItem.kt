package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
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

    Button(
        // Go back button
        onClick = { goBack },
    ) {
        Text("Go back")
    } // End go back button


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
            ) {
                Column() {
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
                        .size(56.dp, 32.dp)
                        .background(
                            color = Color(0xFFfdf1b2),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(8.dp)

                    ) {
                        Icon(Icons.Filled.Star, contentDescription = "none")
                        Text(
                            text = anime.score?.toString() ?: "No rating",
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
                    .padding(top = 8.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Year ",
                        color = Color(0xFF656391),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.year?.toString() ?: "No year",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Type ",
                        color = Color(0xFF656391),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = anime.type ?: "No type",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (anime.type != null) {
                    if (anime.type == "TV") {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Episodes ",
                                color = Color(0xFF656391),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = anime.episodes?.toString() ?: "No episodes",
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else { // End if anime.type == TV
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Duration ",
                                color = Color(0xFF656391),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = anime.duration ?: "No duration",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                                .size(80.dp, 24.dp)
                                .background(
                                    color = Color(0xFF979ffb),
                                    shape = RoundedCornerShape(8.dp)
                                ),
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
                text = "More info",
                color = Color(0xFF656391),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = anime.url.toString(),
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    } // End Main Column for Card

} // End Main Box for Card
