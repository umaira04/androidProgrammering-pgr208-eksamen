package com.example.androideksamen.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androideksamen.data.database.Genre


//source for this func
//https://www.youtube.com/watch?v=5h737wNN-qM

/*
    her ser vi at vi kunne lagt inn listen som en parameter for å gjøre koden mer skalerbar,
    men vi har valgt å droppe det, da vi kun bruker drowdown menyen en gang.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropdownMenu(
    selectedGenre: Genre,
    onGenreSelected: (Genre) -> Unit
) {


    val genreList = listOf(
        Genre.OTHER, Genre.ADVENTURE, Genre.DRAMA, Genre.SCIFI, Genre.ACTION,
        Genre.COMEDY
    )

    var isExpanded: Boolean by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TextField(
                value = selectedGenre.name,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() //TODO: sjekke ut dette
                    .clip(RoundedCornerShape(32.dp)),
                colors = inputTheme(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                genreList.forEach { genre ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = genre.name,
                                color = Onyx
                            )
                        },
                        onClick = {
                            onGenreSelected(genre)
                            isExpanded = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun DropdownMenuPreview() {
}