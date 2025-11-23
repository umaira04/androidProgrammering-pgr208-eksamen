package com.example.androideksamen.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androideksamen.screens.anime.AnimeScreen
import com.example.androideksamen.screens.anime.AnimeViewModel
import com.example.androideksamen.screens.animedetails.AnimeDetailsViewModel
import com.example.androideksamen.screens.animeideas.AnimeIdeasScreen
import com.example.androideksamen.screens.animeideas.AnimeIdeasViewModel
import com.example.androideksamen.screens.animesearch.AnimeSearchScreen
import com.example.androideksamen.screens.animesearch.AnimeSearchViewModel
import com.example.androideksamen.screens.character.CharacterScreen
import com.example.androideksamen.screens.character.CharacterViewModel

@Composable
fun AppNavigation(
    animeViewModel: AnimeViewModel,
    animeSearchViewModel: AnimeSearchViewModel,
    animeIdeasViewModel: AnimeIdeasViewModel,
    characterViewModel: CharacterViewModel,
    animeDetailsViewModel: AnimeDetailsViewModel

){
    val navController = rememberNavController()
    var activeItem by rememberSaveable() { mutableIntStateOf(0) }
    val animeTheme = NavigationBarItemDefaults.colors(
        indicatorColor = Color(0xFF0A0E0D),
        selectedIconColor = Color(0xFFF5F5F5),
        unselectedIconColor = Color(0xFFF5F5F5),
        selectedTextColor = Color(0xFFF5F5F5),
        unselectedTextColor = Color(0xFFF5F5F5)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF656391)
            ) {
                NavigationBarItem( // Start Anime
                    selected = activeItem == 0,
                    onClick = {
                        activeItem = 0
                        navController.navigate(NavRoutes.AnimeRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = { Text("Anime") },
                    colors = animeTheme
                ) // End Anime

                NavigationBarItem( // Start AnimeSearch
                    selected = activeItem == 1,
                    onClick = {
                        activeItem = 1
                        navController.navigate(NavRoutes.AnimeSearchRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    label = { Text("Search") },
                    colors = animeTheme
                ) // End AnimeSearch

                NavigationBarItem( // Start AnimeIdeas
                    selected = activeItem == 2,
                    onClick = {
                        activeItem = 2
                        navController.navigate(NavRoutes.AnimeIdeasRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = null
                        )
                    },
                    label = { Text("Ideas") },
                    colors = animeTheme
                ) // End AnimeIdeas

                NavigationBarItem( // Start Character
                    selected = activeItem == 3,
                    onClick = {
                        activeItem = 3
                        navController.navigate(NavRoutes.CharacterRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null
                        )
                    },
                    label = { Text("Characters") },
                    colors = animeTheme

                ) // End Character
            }
        } // End NavigationBar

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.AnimeRoute
            ) {
                composable <NavRoutes.AnimeRoute> {
                    AnimeScreen(animeViewModel, navController)
                }
                composable <NavRoutes.AnimeSearchRoute> {
                    AnimeSearchScreen(animeSearchViewModel, navController)
                }
                composable <NavRoutes.AnimeIdeasRoute> {
                    AnimeIdeasScreen(animeIdeasViewModel)
                }
                composable <NavRoutes.CharacterRoute> {
                    CharacterScreen(characterViewModel)
                }
                /*composable <NavRoutes.AnimeDetailsRoute> { backStackEntry ->
                    val args = backStackEntry.toRoute<NavRoutes.AnimeDetailsRoute>()
                    AnimeDetailsScreen(
                        animeDetailsViewModel,
                        navController,
                        args.animeId
                    )
                }*/
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    AppNavigation(
        animeViewModel = AnimeViewModel(),
        animeSearchViewModel = AnimeSearchViewModel(),
        animeIdeasViewModel = AnimeIdeasViewModel(),
        characterViewModel = CharacterViewModel(),
        animeDetailsViewModel = AnimeDetailsViewModel()
    )
}


