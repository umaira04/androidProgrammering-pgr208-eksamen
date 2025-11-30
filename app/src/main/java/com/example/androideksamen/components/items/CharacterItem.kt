package com.example.androideksamen.components.items

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.androideksamen.components.shared.DarkBlue
import com.example.androideksamen.components.shared.LightPink
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.data.dataclasses.character.Character
import com.example.androideksamen.fonts.BodyFont

@Composable
fun CharacterItem(
    character: Character,
    isFavorite: Boolean = false,
    onFavClick: () -> Unit = {}
) {

    val context = LocalContext.current

    Box( // Start main box
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = LightPink,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row( // Start main row
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column( // Column med engelsk navn, japansk navn og link
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Engelsk navn
                Text(
                    text = character.name,
                    fontFamily = BodyFont,
                    fontSize = if (character.name.length > 16) 16.sp else 24.sp,
                    color = Onyx,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                // Japansk navn
                Text(
                    text = character.nameJapanese ?: "No japanese name",
                    fontFamily = BodyFont,
                    color = Onyx,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Link til MyAnimeList wiki for karakterer
                Text(
                    text = "Open in MyAnimeList.net",
                    fontFamily = BodyFont,
                    fontSize = 16.sp,
                    color = DarkBlue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, character.url?.toUri())
                            context.startActivity(intent)
                        }
                )
            } // End column med navn og link

            // Hjerte-knapp
            IconButton(
                onClick = onFavClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(40.dp),
                    tint = DarkBlue
                )
            }

            // Bilde
            AsyncImage(
                model = character.characterImage?.jpg?.imageUrl,
                contentDescription = "Bilde av ${character.name}",
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 8.dp)
            )
        } // End main row
    } // End main box
}