package com.example.androideksamen.screens.character

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.components.lists.CharacterList
import com.example.androideksamen.data.dataclasses.character.Character

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel
) {

    val characters by characterViewModel.characters.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7EAF9))
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
    ) {
        // Tittel
        Text(
            text = "Characters",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A0E0D),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        // CharacterList
        CharacterList(
            characterList = characters,
            modifier = Modifier.weight(1f)
        )

    } // End column
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun CharacterScreenPreview() {
    CharacterScreen(
        characterViewModel = CharacterViewModel()
    )
}
