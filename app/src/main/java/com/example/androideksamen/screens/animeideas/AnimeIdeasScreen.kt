package com.example.androideksamen.screens.animeideas

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


    Column {//MAIN COLUMN START
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

        Button(
            onClick = {
                if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                    animeIdeasViewModel.insertAnimeIdea(
                        AnimeDB(title = title, synopsis = synopsis)
                    )
                }
            }
        ) {
            Text("Lagre anime")
        }

        if (animeIdeas.value.count() > 0) {
            LazyColumn {
                items(animeIdeas.value) { animeIdea ->
                    AnimeIdeaItem(animeIdea)
                }
            }
        } else {
            Text("Lagre dine animeideer")
        }

    } //MAIN COLUMN END
}//AnimeIdeasScreen END
