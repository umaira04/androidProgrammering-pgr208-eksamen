package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androideksamen.data.database.AnimeDB


@Composable
fun AnimeIdeaItem(
    animeIdea: AnimeDB,
    handleEditBtnClick: (AnimeDB) -> Unit,
    handleDeleteBtnClick: (AnimeDB) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color(0xFFa4facb))
            .fillMaxWidth()
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(animeIdea.title)
                    Text(animeIdea.synopsis)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                ) {
                    Button(
                        onClick = { handleEditBtnClick(animeIdea) }
                    ) {
                        Icon(Icons.Outlined.Edit, contentDescription = "none")
                    }
                    Button(
                        onClick = { handleDeleteBtnClick(animeIdea) }
                    ) {
                        Icon(Icons.Outlined.Delete, contentDescription = "none")
                    }

                }
            }
        }
    }
}