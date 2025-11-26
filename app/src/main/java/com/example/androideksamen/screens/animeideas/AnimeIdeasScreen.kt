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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.AnimeIdeaList
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.data.database.AnimeDB
import kotlinx.coroutines.delay

@Composable
fun AnimeIdeasScreen(
    animeIdeasViewModel: AnimeIdeasViewModel,
) {

    val animeIdeas = animeIdeasViewModel.animeIdeas.collectAsState()
    var title: String by remember { mutableStateOf("") }
    var synopsis: String by remember { mutableStateOf("") }
    var id: Int by remember { mutableStateOf(value = 0) }

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
        isEditing = true
    }

    fun handleDeleteBtnClick(animeIdea: AnimeDB) {
        isDeleting = true
        animeIdeaToDelete = animeIdea
    }

    Column( // MAIN COLUMN START
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBBAED))
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

    TextField(
        value = title,
        onValueChange = { title = it },
        placeholder = { Text("Enter your anime title...") },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color(0xFFF7EAF9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color(0xFF0A0E0D),
            unfocusedPlaceholderColor = Color(0xFF0A0E0D),
            focusedTextColor = Color(0xFF0A0E0D),
            unfocusedTextColor = Color(0xFF0A0E0D)
        )
    )

    Text(
        text = "Synopsis *",
        fontSize = 16.sp,
        modifier = Modifier
            .padding(horizontal = 24.dp)
    )

    TextField(
        value = synopsis,
        onValueChange = { synopsis = it },
        placeholder = { Text("Describe your anime idea...") },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color(0xFFF7EAF9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color(0xFF0A0E0D),
            unfocusedPlaceholderColor = Color(0xFF0A0E0D),
            focusedTextColor = Color(0xFF0A0E0D),
            unfocusedTextColor = Color(0xFF0A0E0D)
        )
    )

    if (isEditing) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            Button(
                onClick = {
                    if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                        animeIdeasViewModel.updateAnimeIdea(
                            AnimeDB(id = id, title = title, synopsis = synopsis)
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

            Button(
                onClick = {
                    isEditing = false
                    title = ""
                    synopsis = ""
                }
            ) {
                Text("Cancel")
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    if (title.isNotEmpty() && synopsis.isNotEmpty()) {
                        animeIdeasViewModel.insertAnimeIdea(
                            AnimeDB(title = title, synopsis = synopsis)
                        )
                        title = ""
                        synopsis = ""
                        userFeedbackMessage = "Save: Successful"
                    }
                }
            ) {
                Text("Save anime")
            }
            Text(
                text = userFeedbackMessage,
                color = Color(0xFF08864A),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
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
            Text("No anime ideas yet")
        }
    }
    } //MAIN COLUMN END
}//AnimeIdeasScreen END

