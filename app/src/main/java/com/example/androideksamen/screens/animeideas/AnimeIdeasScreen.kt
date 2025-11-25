package com.example.androideksamen.screens.animeideas

import android.util.Log
import androidx.compose.foundation.layout.Column
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
import com.example.androideksamen.components.items.AnimeIdeaItem
import com.example.androideksamen.data.database.AnimeDB

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel,
) {

    val animeIdeas = animeIdeasViewModel.animeIdeas.collectAsState()
    var title: String by remember { mutableStateOf("") }
    var synopsis: String by remember { mutableStateOf("") }
    var id: Int by remember { mutableStateOf(value=0) }

    var isEditing by remember { mutableStateOf(false) }
    //var editedAnimeIdea: AnimeDB? by remember { mutableStateOf(null) }

    fun handleEditBtnClick(animeIdea : AnimeDB) {
        title = animeIdea.title
        synopsis = animeIdea.synopsis
        id = animeIdea.id
        isEditing = true
    }

    fun handleDeleteBtnClick(animeIdea : AnimeDB) {
        animeIdeasViewModel.deleteAnimeIdea(animeIdea)
    }

    Column() {//MAIN COLUMN START
        Text("AnimeIdeasScreen")

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


    if(isEditing) {
        Button(
            onClick = {
                if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                    animeIdeasViewModel.updateAnimeIdea(
                        AnimeDB(id=id, title = title, synopsis = synopsis)
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
