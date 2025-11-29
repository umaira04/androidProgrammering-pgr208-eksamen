package com.example.androideksamen.components.items

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.androideksamen.components.shared.AnimeInfo
import com.example.androideksamen.components.shared.BodyText
import com.example.androideksamen.components.shared.DarkBlue
import com.example.androideksamen.components.shared.DarkPink
import com.example.androideksamen.components.shared.LightYellow
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.components.shared.Subtitle
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.character.Character


//MULIG Å LEGGE TILBAKE KNAPP UTENFOR ITEM?? -U
@Composable
fun AnimeDetailsItem(
    anime: Anime,
    isSearchScreen: Boolean = false, characters: List<Character> = emptyList()

) {
    val context = LocalContext.current


    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = DarkPink,
            )
            .padding(horizontal = 16.dp)
    ) {

        item { // Image
            AsyncImage(
                model = anime.images?.jpg?.imageUrl,
                contentDescription = "Bilde av ${anime.titleEnglish}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }// End image

        item { // Start row with title and rating box
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                        color = Onyx
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text( // Title japanese
                            text = anime.titleJapanese ?: "No japanese title",
                            fontSize = if (anime.titleJapanese.toString().length > 12) 16.sp else 24.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 24.sp,
                            color = Onyx,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        )
                        Box(  // Yellow rating box
                            modifier = Modifier
                                .size(80.dp, 32.dp)
                                .background(
                                    color = LightYellow,
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
                                Icon(Icons.Filled.Star, contentDescription = "none", tint = Onyx)
                                Text(
                                    text = anime.score?.toString() ?: "...",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Onyx
                                )
                            }
                        } // End yellow rating box
                    } // End row with japanese title and rating bow
                }
            } // End row
        }

        item {  // Row with anime info: Year, Episodes, Type
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AnimeInfo(
                    subtitle = "Year",
                    animeInfo = anime.year?.toString() ?: "No year"
                )
                AnimeInfo(
                    subtitle = "Type",
                    animeInfo = anime.type ?: "No type"
                )

                if (anime.type != null) {
                    if (anime.type == "TV") {
                        AnimeInfo(
                            subtitle = "Episodes",
                            animeInfo = anime.episodes?.toString() ?: "No episodes"
                        )
                    } else { // End if anime.type == TV
                        AnimeInfo(
                            subtitle = "Duration",
                            animeInfo = anime.duration ?: "No duration"
                        )
                    } // End else if anime.type != TV
                } // End if anime.type != null
            } // End row with anime info: Year, Episodes, Type
        } // End item

        item {  // Synposis
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Subtitle("Synopsis")
                BodyText(
                    anime.synopsis ?: "No synopsis",
                )
            }
        } // End synopsis

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Subtitle("Genres")
                Row( // Start row genres
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    anime.genres?.forEach { genre ->
                        Box(
                            modifier = Modifier
                                .height(32.dp)
                                .background(
                                    color = LightYellow,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = genre.name.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                color = Onyx
                            )
                        }
                    }
                }
            } // End row genres
        }

        if (isSearchScreen) {
            item {
                Subtitle("Main characters")
            }
            items(characters) { character ->
                MainCharacterItem(character = character)
            }
        }

        item {
            Subtitle("More information")
            Text(
                text = anime.url.toString(),
                color = DarkBlue,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, anime.url?.toUri())
                        context.startActivity(intent)
                    }
                    .padding(bottom = 8.dp)
            )
        } // End more information
    } // End Main LazyColumn
} // End AnimeDetailsItem


