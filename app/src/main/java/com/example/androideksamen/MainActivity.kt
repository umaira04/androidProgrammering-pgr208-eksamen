package com.example.androideksamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androideksamen.data.database.AnimeDbRepository
import com.example.androideksamen.navigation.AppNavigation
import com.example.androideksamen.screens.anime.AnimeViewModel
import com.example.androideksamen.screens.animedetails.AnimeDetailsViewModel
import com.example.androideksamen.screens.animeideas.AnimeIdeasViewModel
import com.example.androideksamen.screens.animesearch.AnimeSearchViewModel
import com.example.androideksamen.screens.character.CharacterViewModel
import com.example.androideksamen.ui.theme.AndroidEksamenTheme

class MainActivity : ComponentActivity() {
    private val _animeViewModel: AnimeViewModel by viewModels()
    private val _animeSearchViewModel: AnimeSearchViewModel by viewModels()
    private val _animeIdeasViewModel: AnimeIdeasViewModel by viewModels()
    private val _characterViewModel: CharacterViewModel by viewModels()
    private val _animeDetailsViewModel: AnimeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AnimeDbRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            AndroidEksamenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation(
                            _animeViewModel,
                            _animeSearchViewModel,
                            _animeIdeasViewModel,
                            _characterViewModel,
                            _animeDetailsViewModel
                        )
                    }
                }
            }
        }
    }
}
