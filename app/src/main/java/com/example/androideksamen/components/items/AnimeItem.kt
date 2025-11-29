package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androideksamen.components.shared.LightPink
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.data.dataclasses.anime.Anime

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
                color = LightPink,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text( // English
                text = anime.titleEnglish ?: "No english title",
                fontSize = if (anime.titleEnglish.toString().length > 20) 24.sp else 32.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Onyx,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box( // Main box
                modifier = Modifier
                    .fillMaxWidth()
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
                    color = Onyx,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        // Fant ingen annen måte å plassere rotert tekst, så vi brukte Claude Opus 4.5. Dette var prompten:
                        // "hvordan kan jeg plassere titleJapanese til høyre når jeg bruker rotate(90f)? dette er koden min:" (også hele AnimeItem)
                        .offset(x = 144.dp)
                        .rotate(90f)
                )
            } // End main box

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = anime.genres?.firstOrNull()?.name ?: "No genre",
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Onyx,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = anime.year?.toString() ?: "No year",
                    fontSize = 24.sp,
                    maxLines = 1,
                    color = Onyx,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}