package com.example.androideksamen.components.lists

import androidx.compose.runtime.Composable
import com.example.androideksamen.components.items.AnimeIdeaItem
import com.example.androideksamen.data.database.AnimeDB


@Composable
fun AnimeIdeaList(
    animeIdeas: List<AnimeDB>,
    handleEditBtnClick: (animeIdea: AnimeDB) -> Unit,
    handleDeleteBtnClick: (animeIdea: AnimeDB) -> Unit

) {

    animeIdeas.forEach { animeIdea ->
        AnimeIdeaItem(
            animeIdea,
            handleEditBtnClick = { handleEditBtnClick(animeIdea) },
            handleDeleteBtnClick = { handleDeleteBtnClick(animeIdea) }
        )
    }

}