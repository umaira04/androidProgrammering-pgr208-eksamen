package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.data.database.AnimeDB

//TODO: LAGE ET ITEM SOM KAN VISE IDEER SOM BRUKEREN HAR LAGRET

@Composable
fun AnimeIdeaItem(
    animeIdea: AnimeDB,
    handleEditBtnClick: (AnimeDB) -> Unit,
    handleDeleteBtnClick: (AnimeDB) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color(0xFF90FDE6),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .padding(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { handleDeleteBtnClick(animeIdea) },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(25.dp))
                        ) {
                            Icon(Icons.Outlined.Delete, contentDescription = "none")
                        }
                        IconButton(
                            onClick = { handleEditBtnClick(animeIdea) },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(25.dp))
                        ) {
                            Icon(Icons.Outlined.Edit, contentDescription = "none")
                        }

                    }
                    Text(
                        text = "Title",
                    color = Color(0xFF656391),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                    )
                    Text(animeIdea.title)
                    Text(
                        text = "Synopsis",
                        color = Color(0xFF656391),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(animeIdea.synopsis)
                }
            }
        }
    }
}