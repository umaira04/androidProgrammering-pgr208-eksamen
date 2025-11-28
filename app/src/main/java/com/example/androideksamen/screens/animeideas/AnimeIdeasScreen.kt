package com.example.androideksamen.screens.animeideas

import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.AnimeIdeaList
import com.example.androideksamen.components.shared.GenreDropdownMenu
import com.example.androideksamen.components.shared.InputTextField
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.data.database.AnimeDB
import com.example.androideksamen.data.database.Genre
import kotlinx.coroutines.delay

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel,
) {

    // TODO? BØR STATES LIGGE I VIEWMODEL?

    val animeIdeas = animeIdeasViewModel.animeIdeas.collectAsState()
    var title: String by remember { mutableStateOf("") }
    var synopsis: String by remember { mutableStateOf("") }
    var id: Int by remember { mutableStateOf(value = 0) }
    var genre: Genre by remember { mutableStateOf(value = Genre.OTHER) }

    var isEditing by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    var animeIdeaToDelete: AnimeDB? by remember { mutableStateOf(null) }
    var userFeedbackMessage by remember { mutableStateOf("") }

    LaunchedEffect(userFeedbackMessage) {
        if (userFeedbackMessage.isNotEmpty()) {
            delay(3000)
            userFeedbackMessage = ""
        }
    }

    fun handleEditBtnClick(animeIdea: AnimeDB) {
        title = animeIdea.title
        synopsis = animeIdea.synopsis
        id = animeIdea.id
        genre = animeIdea.genre
        isEditing = true
    }

    fun handleDeleteBtnClick(animeIdea: AnimeDB) {
        isDeleting = true
        animeIdeaToDelete = animeIdea
    }

    Column( // MAIN COLUMN START
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBBAED))
            .padding(16.dp, 8.dp, 16.dp, 0.dp)
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
                .padding(horizontal = 16.dp)
        )

        InputTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = "Enter your anime title..."
        )

        Text(
            text = "Synopsis *",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        InputTextField(
            value = synopsis,
            onValueChange = { synopsis = it },
            placeholder = "Describe your anime idea...")

        Text(
            text = "Genre",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        GenreDropdownMenu(
            selectedGenre = genre,
            onGenreSelected = { genre = it }
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
                    onClick = {
                        isEditing = false
                        title = ""
                        synopsis = ""
                    }
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                            animeIdeasViewModel.updateAnimeIdea(
                                AnimeDB(
                                    id = id,
                                    title = title,
                                    synopsis = synopsis,
                                    genre = genre)
                            )

                            isEditing = false
                            title = ""
                            synopsis = ""
                            userFeedbackMessage = "Update: Successful"
                        }
                    }
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
                    text = userFeedbackMessage,
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
                            title = ""
                            synopsis = ""
                            userFeedbackMessage = "Save: Successful"
                        }
                    }
                ) {
                    Text("Save anime")
                }
            }
        }

        if (isDeleting) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
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
                    Text("Are you sure you want to delete: ")
                    Text(
                        text = animeIdeaToDelete?.title ?: "Unknown",
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
                                isDeleting = false
                                animeIdeaToDelete = null
                                userFeedbackMessage = "Delete: Successful"
                            }
                        ) {
                            Text("Delete")
                        }

                        Button(
                            onClick = {
                                isDeleting = false
                                animeIdeaToDelete = null
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        } else {
            if (animeIdeas.value.count() > 0 && !isDeleting) {
                AnimeIdeaList(
                    animeIdeas = animeIdeas.value,
                    handleEditBtnClick = { handleEditBtnClick(animeIdea = it) },
                    handleDeleteBtnClick = { handleDeleteBtnClick(animeIdea = it) }
                )
            } else {
                Text("No anime ideas yet...♥",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 56.dp)
                        .fillMaxWidth()
                )
            }
        }
    } //MAIN COLUMN END
}//AnimeIdeasScreen END


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AnimeIdeasScreenPreview() {
    AnimeIdeasScreen(
        animeIdeasViewModel = AnimeIdeasViewModel()
    )
}

