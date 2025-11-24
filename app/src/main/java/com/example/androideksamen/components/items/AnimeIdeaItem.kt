package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.androideksamen.data.database.AnimeDB

//TODO: LAGE ET ITEM SOM KAN VISE IDEER SOM BRUKEREN HAR LAGRET

@Composable
fun AnimeIdeaItem(animeIdea: AnimeDB) {
    Box(modifier = Modifier.background(Color(0xffff00ff))) {
        Column {
            Text(animeIdea.title)
            Text(animeIdea.synopsis)
        }
    }

}