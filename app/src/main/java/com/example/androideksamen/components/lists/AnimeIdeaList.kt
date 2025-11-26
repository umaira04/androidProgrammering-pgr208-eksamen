package com.example.androideksamen.components.lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androideksamen.components.items.AnimeIdeaItem
import com.example.androideksamen.data.database.AnimeDB


@Composable
fun AnimeIdeaList(
    animeIdeas: List<AnimeDB>,
    handleEditBtnClick: (animeIdea: AnimeDB) -> Unit,
    handleDeleteBtnClick: (animeIdea: AnimeDB) -> Unit

) {
    LazyColumn(modifier = Modifier) {
        items(animeIdeas) { animeIdea ->
            AnimeIdeaItem(
                animeIdea,
                handleEditBtnClick = { handleEditBtnClick(animeIdea) },
                handleDeleteBtnClick = { handleDeleteBtnClick(animeIdea) }
            )
        }
    }
}