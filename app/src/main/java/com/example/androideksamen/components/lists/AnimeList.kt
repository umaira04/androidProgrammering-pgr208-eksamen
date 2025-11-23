package com.example.androideksamen.components.lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androideksamen.components.items.AnimeItemTest
import com.example.androideksamen.data.dataclasses.anime.Anime


@Composable
fun AnimeList(
    animeList: List<Anime>,
    onAnimeClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier) {
        items(animeList) { anime ->
            AnimeItemTest(
                anime = anime,
                showDetails = { onAnimeClicked(anime.id) }
            )
        }
    }
}

