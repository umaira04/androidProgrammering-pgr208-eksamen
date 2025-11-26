package com.example.androideksamen.components.lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.androideksamen.components.items.AnimeItem
import com.example.androideksamen.data.dataclasses.anime.Anime


@Composable
fun AnimeList(
    animeList: List<Anime>,
    onAnimeClicked: (Int) -> Unit,
) {
    LazyColumn {
        items(animeList) { anime ->
            AnimeItem(
                anime = anime,
                showDetails = { onAnimeClicked(anime.id) }
            )
        }
    }
}

