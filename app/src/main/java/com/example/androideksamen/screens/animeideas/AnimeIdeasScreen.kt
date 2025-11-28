package com.example.androideksamen.screens.animeideas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.AnimeIdeaList
import com.example.androideksamen.components.shared.DarkPink
import com.example.androideksamen.components.shared.GenreDropdownMenu
import com.example.androideksamen.components.shared.InputTextField
import com.example.androideksamen.components.shared.LightPink
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.components.shared.buttonTheme
import com.example.androideksamen.data.database.AnimeDB
import com.example.androideksamen.data.database.Genre
import kotlinx.coroutines.delay

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel,
) {

    // TODO? BØR STATES LIGGE I VIEWMODEL?
    // TODO: LazyColumn på hele siden

    val animeIdeas by animeIdeasViewModel.animeIdeas.collectAsState()
    val title by animeIdeasViewModel.title.collectAsState()
    val synopsis by animeIdeasViewModel.synopsis.collectAsState()
    val id by animeIdeasViewModel.id.collectAsState()
    val genre by animeIdeasViewModel.genre.collectAsState()
    val isEditing by animeIdeasViewModel.isEditing.collectAsState()
    val showFeedbackMessage by animeIdeasViewModel.userFeedbackMessage.collectAsState()

    var isDeleting by remember { mutableStateOf(false) }
    var animeIdeaToDelete: AnimeDB? by remember { mutableStateOf(null) }



    /*fun handleEditBtnClick(animeIdea: AnimeDB) {
        title = animeIdea.title
        synopsis = animeIdea.synopsis
        id = animeIdea.id
        genre = animeIdea.genre
        isEditing = true
    }*/

    fun handleDeleteBtnClick(animeIdea: AnimeDB) {
        isDeleting = true
        animeIdeaToDelete = animeIdea
    }

    Column( // MAIN COLUMN START
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPink)
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {
        Title("Anime Ideas")

        Text(
            "Make your personal anime ideas!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "Title *",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        InputTextField(
            value = title,
            onValueChange = { animeIdeasViewModel.setTitle(it) },
            placeholder = "Enter your anime title..."
        )

        Text(
            text = "Synopsis *",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        InputTextField(
            value = synopsis,
            onValueChange = { animeIdeasViewModel.setSynopsis(it) },
            placeholder = "Describe your anime idea..."
        )

        Text(
            text = "Genre",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        GenreDropdownMenu(
            selectedGenre = genre,
            onGenreSelected = { animeIdeasViewModel.setGenre(it) }
        )

        if (isEditing) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {

                Button(
                    onClick = { animeIdeasViewModel.cancelEditing() },
                    colors = buttonTheme()
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                            animeIdeasViewModel.updateAnimeIdea(
                                AnimeDB( id, title, synopsis, genre)
                            )
                            animeIdeasViewModel.cancelEditing()
                            animeIdeasViewModel.showFeedbackMessage("Update: Successful")
                        }
                    },
                    colors = buttonTheme()
                ) {
                    Text("Save changes")
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Text(
                    showFeedbackMessage,
                    color = Onyx,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                            animeIdeasViewModel.insertAnimeIdea(
                                AnimeDB(title = title, synopsis = synopsis, genre = genre)
                            )
                            animeIdeasViewModel.clearForm()
                            animeIdeasViewModel.showFeedbackMessage("Save: Successful")
                        }
                    },
                    colors = buttonTheme()
                ) {
                    Text(
                        "Save anime"
                    )
                }
            }
        }

        if (isDeleting) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(LightPink)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = "none",
                        tint = Color.Red,
                        modifier = Modifier.size(64.dp)
                    )
                    Text("Are you sure you want to delete: ")
                    Text(
                        text = animeIdeaToDelete?.title ?: "unknown",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 16.dp)

                    ) {
                        Button(
                            onClick = {
                                animeIdeaToDelete?.let { animeIdea ->
                                    animeIdeasViewModel.deleteAnimeIdea(animeIdea)
                                }
                                animeIdeasViewModel.clearForm()
                                animeIdeasViewModel.showFeedbackMessage("Delete: Successful")
                            },
                            colors = buttonTheme()
                        ) {
                            Text("Delete")
                        }

                        Button(
                            onClick = {
                                isDeleting = false
                                animeIdeaToDelete = null
                            },
                            colors = buttonTheme()
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        } else {
            if (animeIdeas.isNotEmpty() && !isDeleting) {
                AnimeIdeaList(
                    animeIdeas = animeIdeas,
                    handleEditBtnClick = { animeIdeasViewModel.handleEditBtnClick(it) },
                    handleDeleteBtnClick = { handleDeleteBtnClick(it) }
                )
            } else {
                Text(
                    "No anime ideas yet...♥",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Onyx,
                    modifier = Modifier
                        .padding(top = 56.dp)
                        .fillMaxWidth()
                )
            }
        }
    } //MAIN COLUMN END
}//AnimeIdeasScreen END

