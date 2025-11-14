package com.example.androideksamen.screens.anime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androideksamen.components.items.AnimeItem
import com.example.androideksamen.data.api.Anime
import com.example.androideksamen.navigation.NavRoutes

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    navController: NavController
) {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            "Anime List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, 8.dp)

        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(mockAnime) { anime ->
                AnimeItem(
                    anime = anime,
                    showDetails = {
                        navController.navigate(
                            NavRoutes.AnimeDetailsRoute(anime.id)
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeScreenPreview(){
    AnimeScreen(
        animeViewModel = AnimeViewModel(),
        navController = rememberNavController()
    )
}
