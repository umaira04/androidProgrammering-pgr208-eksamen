package com.example.androideksamen.screens.anime

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androideksamen.components.lists.AnimeList
import com.example.androideksamen.data.api.Anime
import com.example.androideksamen.navigation.NavRoutes

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {
    val focusManager = LocalFocusManager.current
    var searchQuery by remember { mutableStateOf("") }

    val mockAnime = listOf(
        Anime(1, "Pokemon", "Action", 2001),
        Anime(2, "Naruto", "Action", 1999),
        Anime(3, "Death Note","Action", 1988),
        Anime(4, "One Piece", "Action", 1998),
        Anime(5, "kajdwd", "Action", 2004),
        Anime(6, "awodiawd", "Action", 2005),
        Anime(7, "wldkwdk", "Action", 2010),
        Anime(8, "wpdkwd", "Action", 1995),
        Anime(9, "kjkjkjkj", "Action", 2001),
        Anime(10, "wdkwldkwd", "Action", 2000)
    )

    val filteredAnimes = mockAnime.filter { anime ->
        anime.title.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Tittel
        Text(
            "Anime List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, 8.dp)
        )

        // AnimeList
        AnimeList(
            animeList = filteredAnimes,
            modifier = Modifier.weight(1f),
            onAnimeClicked = { animeId ->
                navController.navigate(
                    NavRoutes.AnimeDetailsRoute(animeId)
                )
            }
        )

        // Søkefelt
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Søk etter anime") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            colors = TextFieldDefaults.colors(
                //focusedContainerColor = Color(),
                //unfocusedContainerColor = Color(),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    } // End column
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AnimeScreenPreview(){
    AnimeScreen(
        animeViewModel = AnimeViewModel(),
        navController = rememberNavController()
    )
}
