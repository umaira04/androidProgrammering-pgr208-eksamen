package com.example.androideksamen.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.example.androideksamen.screens.anime.AnimeScreen
import com.example.androideksamen.screens.anime.AnimeViewModel
import com.example.androideksamen.screens.animedetails.AnimeDetailsScreen
import com.example.androideksamen.screens.animedetails.AnimeDetailsViewModel
import com.example.androideksamen.screens.animeideas.AnimeIdeasScreen
import com.example.androideksamen.screens.animeideas.AnimeIdeasViewModel
import com.example.androideksamen.screens.animesearch.AnimeSearchScreen
import com.example.androideksamen.screens.animesearch.AnimeSearchViewModel
import com.example.androideksamen.screens.character.CharacterScreen
import com.example.androideksamen.screens.character.CharacterViewModel
import com.example.androideksamen.screens.home.HomeScreen
import com.example.androideksamen.screens.home.HomeViewModel

@Composable
fun AppNavigation(
    homeViewModel: HomeViewModel,
    animeViewModel: AnimeViewModel,
    animeSearchViewModel: AnimeSearchViewModel,
    animeIdeasViewModel: AnimeIdeasViewModel,
    characterViewModel: CharacterViewModel,
    animeDetailsViewModel: AnimeDetailsViewModel

){
    val navController = rememberNavController()
    var activeItem by rememberSaveable() {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF212121)
            ) {
                NavigationBarItem( // Start Home
                    selected = activeItem == 0,
                    onClick = {
                        activeItem = 0
                        navController.navigate(NavRoutes.HomeRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    }
                ) // End Home

                NavigationBarItem( // Start Anime
                    selected = activeItem == 1,
                    onClick = {
                        activeItem = 1
                        navController.navigate(NavRoutes.AnimeRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                ) // End Anime

                NavigationBarItem( // Start AnimeSearch
                    selected = activeItem == 2,
                    onClick = {
                        activeItem = 2
                        navController.navigate(NavRoutes.AnimeSearchRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                ) // End AnimeSearch

                NavigationBarItem( // Start AnimeIdeas
                    selected = activeItem == 3,
                    onClick = {
                        activeItem = 3
                        navController.navigate(NavRoutes.AnimeIdeasRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = null
                        )
                    }
                ) // End AnimeIdeas

                NavigationBarItem( // Start Character
                    selected = activeItem == 4,
                    onClick = {
                        activeItem = 4
                        navController.navigate(NavRoutes.CharacterRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                ) // End Character
            }
        } // End NavigationBar

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.HomeRoute
            ) {
                composable <NavRoutes.HomeRoute> {
                    HomeScreen(homeViewModel)
                }
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
                composable <NavRoutes.AnimeDetailsRoute> { backStackEntry ->
                    val args = backStackEntry.toRoute<NavRoutes.AnimeDetailsRoute>()
                    AnimeDetailsScreen(
                        animeDetailsViewModel,
                        navController,
                        args.animeId
                    )
                }
            }
        }
    }
}




