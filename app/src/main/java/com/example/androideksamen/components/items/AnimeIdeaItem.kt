package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.shared.DarkBlue
import com.example.androideksamen.components.shared.DustyGrape
import com.example.androideksamen.components.shared.LightPink
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.data.database.AnimeDB


@Composable
fun AnimeIdeaItem(
    animeIdea: AnimeDB,
    handleEditBtnClick: (AnimeDB) -> Unit,
    handleDeleteBtnClick: (AnimeDB) -> Unit
) {
    Box( // Start main box
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .background(
                color = LightPink,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            // Start main column
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row( // Start row med knapper for slett og rediger
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = DustyGrape,
                        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
                    )
                    .padding(8.dp)
            ) {
                // Slett-knapp
                IconButton(
                    onClick = { handleDeleteBtnClick(animeIdea) },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.White)
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = DarkBlue
                    )
                }

                // Rediger-knapp
                IconButton(
                    onClick = { handleEditBtnClick(animeIdea) },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.White)
                ) {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = "Edit",
                        tint = DarkBlue
                    )
                }
            } // End row med knapper

            // Tekst i ide-boksene
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = animeIdea.title,
                    color = Onyx,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = animeIdea.genre.toString(),
                    color = Onyx,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = animeIdea.synopsis,
                    color = Onyx,
                    fontSize = 16.sp,
                )
            }
        } // End main column
    } // End main box
}
