package com.example.androideksamen.screens.animeideas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.items.AnimeIdeaItem
import com.example.androideksamen.data.database.AnimeDB

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel,
) {

    val animeIdeas = animeIdeasViewModel.animeIdeas.collectAsState()
    var title: String by remember { mutableStateOf("") }
    var synopsis: String by remember { mutableStateOf("") }
    var id: Int by remember { mutableStateOf(value = 0) }

    var isEditing by remember { mutableStateOf(false) }
    //var editedAnimeIdea: AnimeDB? by remember { mutableStateOf(null) }

    fun handleEditBtnClick(animeIdea: AnimeDB) {
        title = animeIdea.title
        synopsis = animeIdea.synopsis
        id = animeIdea.id
        isEditing = true
    }

    fun handleDeleteBtnClick(animeIdea: AnimeDB) {
        animeIdeasViewModel.deleteAnimeIdea(animeIdea)
    }

    Column ( //MAIN COLUMN START
        verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFDF1B2))
                .padding(8.dp, 8.dp, 8.dp, 0.dp)

    ){
        Text(
            "Anime ideas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF324663),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        Text(
            "Make your personal anime ideas!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Tittel") }
        )
        TextField(
            value = synopsis,
            onValueChange = { synopsis = it },
            label = { Text("Synopsis") }
        )


        if (isEditing) {
            Button(
                onClick = {
                    if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                        animeIdeasViewModel.updateAnimeIdea(
                            AnimeDB(id = id, title = title, synopsis = synopsis)
                        )

                        isEditing = false
                        title = ""
                        synopsis = ""
                    }
                }
            ) {
                Text("Save changes")
            }

            Button(
                onClick = {
                    isEditing = false
                    title = ""
                    synopsis = ""
                }
            ) {
                Text("Cancel")
            }
        } else {

            Button(
                onClick = {
                    if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                        animeIdeasViewModel.insertAnimeIdea(
                            AnimeDB(title = title, synopsis = synopsis)
                        )
                    }
                }
            ) {
                Text("Save anime")
            }
        }

        if (animeIdeas.value.count() > 0) {
            LazyColumn {
                items(animeIdeas.value) { animeIdea ->
                    AnimeIdeaItem(
                        animeIdea,
                        handleEditBtnClick = { handleEditBtnClick(animeIdea = it) },
                        handleDeleteBtnClick = { handleDeleteBtnClick(animeIdea = it) }
                    )
                }
            }
        } else {
            Text("No anime ideas found")
        }

    } //MAIN COLUMN END
}//AnimeIdeasScreen END
