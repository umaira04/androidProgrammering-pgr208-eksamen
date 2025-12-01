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

@Composable
fun MainCharacterItem(
    character: Character
) {
    val context = LocalContext.current

    Box { // Start main box
        Row( // Start main row
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .background(
                    color = LightPink,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column( // Column med engelsk navn og link
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)

            ) {
                // Engelsk navn
                Text(
                    text = character.name,
                    fontSize = if (character.name.length > 16) 16.sp else 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Onyx,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Link til MyAnimeList wiki for karakter
                Text(
                    text = "Open in MyAnimeList.net",
                    color = DarkBlue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, character.url?.toUri())
                            context.startActivity(intent)
                        }
                )
            } // End column med engelsk navn og link

            // Bilde
            AsyncImage(
                model = character.characterImage?.jpg?.imageUrl,
                contentDescription = "Image of ${character.name}",
                modifier = Modifier
                    .size(80.dp)
            )
        }
    } // End main box
}