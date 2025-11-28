package com.example.androideksamen.screens.animesearch

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.R
import com.example.androideksamen.components.items.AnimeDetailsItem
import com.example.androideksamen.components.shared.DarkPink
import com.example.androideksamen.components.shared.ErrorLoading
import com.example.androideksamen.components.shared.Onyx
import com.example.androideksamen.components.shared.Title
import com.example.androideksamen.components.shared.inputTheme


//FORELESNING 9 FOR Å SE AKKURAT DENNE OPPGAVEN
@Composable
fun AnimeSearchScreen(
    animeSearchViewModel: AnimeSearchViewModel,

    ) {
    val anime by animeSearchViewModel.anime.collectAsState()
    val mainCharacters by animeSearchViewModel.mainCharacters.collectAsState()
    var id by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var isSearched by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(DarkPink)
            .fillMaxSize()
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {
        // Tittel
        Title("Search")

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (anime == null && isSearched) {
                ErrorLoading(2)
            } else if (!isSearched) {
                Text(
                    text = "Search for anime by ID",
                    fontSize = 18.sp,
                    color = Onyx,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(R.drawable.searchicon_img),
                    contentDescription = "image of a search icon. decorative",
                    modifier = Modifier.width(200.dp)
                )
            } else {
                anime?.let { anime ->
                    AnimeDetailsItem(
                        anime = anime,
                        isSearchScreen = true,
                        characters = mainCharacters
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(top = 0.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = id,
                onValueChange = { id = it },
                placeholder = { Text("Search by ID") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(
                    onGo = {
                        val idParsed = id.toIntOrNull()
                        if (idParsed != null) {
                            animeSearchViewModel.setAnimeById(idParsed)
                            animeSearchViewModel.setAnimeMainCharactersByAnimeId(idParsed)
                            isSearched = true
                            Log.i("searchItem", anime.toString())
                        }
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .fillMaxWidth(),
                colors = inputTheme()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeSearchScreenPreview() {
    AnimeSearchScreen(
        animeSearchViewModel = AnimeSearchViewModel(),

        )
}
