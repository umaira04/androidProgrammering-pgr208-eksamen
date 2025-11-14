package com.example.androideksamen.components.lists


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androideksamen.components.items.AnimeItem
import com.example.androideksamen.data.api.Anime


@Composable
fun AnimeList(
    animeList: List<Anime>,
    onAnimeClicked: (Int) -> Unit
){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(animeList) { anime ->
            AnimeItem(
                anime = anime,
                showDetails = { onAnimeClicked(anime.id) }
            )
        }
    }
}

