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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel
) {

    // State fra ViewModel
    val animeIdeas by animeIdeasViewModel.animeIdeas.collectAsState()
    val title by animeIdeasViewModel.title.collectAsState()
    val synopsis by animeIdeasViewModel.synopsis.collectAsState()
    val id by animeIdeasViewModel.id.collectAsState()
    val genre by animeIdeasViewModel.genre.collectAsState()
    val isEditing by animeIdeasViewModel.isEditing.collectAsState()
    val showFeedbackMessage by animeIdeasViewModel.userFeedbackMessage.collectAsState()
    val isDeleting by animeIdeasViewModel.isDeleting.collectAsState()
    val animeIdeaToDelete by animeIdeasViewModel.animeIdeaToDelete.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPink)
            .padding(16.dp, 8.dp, 16.dp, 0.dp)
    ) {
        item { // Wrapper for main column start
            Column( // Main column med alt innhold start
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tittel
                Title("Anime Ideas")

                Text(
                    "Make your personal anime ideas!",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    color = Onyx
                )

                // Input
                Text(
                    text = "Title *",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = Onyx
                )

                InputTextField(
                    value = title,
                    onValueChange = { animeIdeasViewModel.setTitle(it) },
                    placeholder = "Enter your anime title..."
                )

                Text(
                    text = "Synopsis *",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = Onyx
                )

                InputTextField(
                    value = synopsis,
                    onValueChange = { animeIdeasViewModel.setSynopsis(it) },
                    placeholder = "Describe your anime idea..."
                )

                Text(
                    text = "Genre",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = Onyx
                )

                GenreDropdownMenu(
                    selectedGenre = genre, onGenreSelected = { animeIdeasViewModel.setGenre(it) })

                // Knapper
                if (isEditing) {
                    // Kanseller og lagre endringer
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 0.dp)
                    ) {
                        Button(
                            onClick = { animeIdeasViewModel.cancelEditing() },
                            colors = buttonTheme()
                        ) { Text("Cancel") }
                        Button(
                            onClick = {
                                if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                                    animeIdeasViewModel.updateAnimeIdea(
                                        AnimeDB(
                                            id, title, synopsis, genre
                                        )
                                    )
                                    animeIdeasViewModel.cancelEditing()
                                    animeIdeasViewModel.showFeedbackMessage("Update: Successful")
                                }
                            }, colors = buttonTheme()
                        ) { Text("Save changes") }
                    }
                } else {
                    // Feedback og lagre ny
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 0.dp)
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
                                        AnimeDB(
                                            title = title, synopsis = synopsis, genre = genre
                                        )
                                    )
                                    animeIdeasViewModel.clearForm()
                                    animeIdeasViewModel.showFeedbackMessage("Save: Successful")
                                }
                            }, colors = buttonTheme()
                        ) {
                            Text("Save anime")
                        }
                    }
                }

                // Slett
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
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier.size(64.dp)
                            )
                            Text("Are you sure you want to delete: ", color = Onyx)
                            Text(
                                text = animeIdeaToDelete?.title ?: "Unknown",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Onyx
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(top = 16.dp)

                            ) {
                                Button(
                                    onClick = { animeIdeasViewModel.confirmDelete() },
                                    colors = buttonTheme()
                                ) {
                                    Text("Delete")
                                }

                                Button(
                                    onClick = { animeIdeasViewModel.cancelDelete() },
                                    colors = buttonTheme()
                                ) {
                                    Text("Cancel")
                                }
                            }
                        }
                    }
                } else {
                    // Viser liste eller melding om at liste er tom
                    if (animeIdeas.isNotEmpty()) {
                        AnimeIdeaList(
                            animeIdeas = animeIdeas,
                            handleEditBtnClick = { animeIdeasViewModel.handleEditBtnClick(it) },
                            handleDeleteBtnClick = { animeIdeasViewModel.handleDeleteBtnClick(it) })
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
            } // Main column end
        } // Item end
    }
}

