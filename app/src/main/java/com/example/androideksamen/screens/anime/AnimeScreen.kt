package com.example.androideksamen.screens.anime

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
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
import com.example.androideksamen.data.dataclasses.anime.Anime
import com.example.androideksamen.data.dataclasses.anime.Genre
import com.example.androideksamen.navigation.NavRoutes

//TODO: KNAPP FOR FAVORITTER SOM VISER KUN FAVORITT ANIMEER
//TODO: HJERTEKNAPP TIL Å VELGE FAVORITTER
//TODO: FILTER PÅ SJANGER
@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {

    val mockAnime = listOf(
        Anime(1, null, genres = listOf(Genre(name = "Action")), titleEnglish = "Pokemon", titleJapanese = "Pokemon Japan" ,year = 2001),
        Anime(2, null, genres = listOf(Genre(name = "Action")), titleEnglish = "Naruto", titleJapanese = "Pokemon Japan" ,year =  1999),
        Anime(3, null, genres = listOf(Genre(name = "Action")), titleEnglish = "One Piece", titleJapanese = "Pokemon Japan" ,year =  1988),
        Anime(4, null, genres = listOf(Genre(name = "Action")), titleEnglish = "kwjdw", titleJapanese = "Pokemon Japan" ,year =  1998),
        Anime(5, null, genres = listOf(Genre(name = "Action")), titleEnglish = "wkfjwkfj", titleJapanese = "Pokemon Japan", year =  2004),
        Anime(6, null, genres = listOf(Genre(name = "Action")), titleEnglish = "owdowdo", titleJapanese = "Pokemon Japan", year =  2005),
        Anime(7, null, genres = listOf(Genre(name = "Action")), titleEnglish = "popwoepo", titleJapanese = "Pokemon Japan", year =  2010),
        Anime(8, null, genres = listOf(Genre(name = "Action")), titleEnglish = "qewewewe", titleJapanese = "Pokemon Japan" ,year =  1995),
        Anime(9, null, genres = listOf(Genre(name = "Action")), titleEnglish = "wdwdwdwd", titleJapanese = "Pokemon Japan" ,year =  2001),
        Anime(10, null, genres = listOf(Genre(name = "Action")), titleEnglish = "asasdasd", titleJapanese = "Pokemon Japan" ,year =  2000)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7EAF9))
            .padding(8.dp)
    ) {
        // Tittel
        Text(
            "Anime List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A0E0D),
            modifier = Modifier.padding(start = 8.dp, 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // AnimeList
        AnimeList(
            animeList = mockAnime,
            modifier = Modifier.weight(1f),
            onAnimeClicked = { animeId ->
                navController.navigate(
                    NavRoutes.AnimeDetailsRoute(animeId)
                )
            }
        )

        // Vis favoritter


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
