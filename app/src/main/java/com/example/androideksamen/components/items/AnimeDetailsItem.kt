package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androideksamen.data.api.Anime


//anime : Anime SKAL TAS MOT SOM PARAMETER
@Composable
fun AnimeDetailsItem() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color(0xFFD0D0D0),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {


            Box( // PLACEHOLDER FOR BILDE
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(120.dp, 80.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) // End image placeholder box

            Text("Title English: Pokemon")
            Text("Title Japanese: PokemonJapanese")
            Text("Year: 1996 ")
            Text("Rating: 8.9 ")
            Text("Type: TV")
            Text("Number of episodes: 215")
            Text("Synopsis: Pokémon the Series primarily follows Ash Ketchum, a young boy from Pallet Town who dreams of becoming a Pokémon Master. After receiving his first Pokémon, Pikachu, from Professor Oak, Ash embarks on a journey across various regions — including Kanto, Johto, Hoenn, Sinnoh, Unova, Kalos, Alola, and Galar — where he challenges Gym Leaders, competes in regional Pokémon Leagues, or other competitions, and meets a variety of companions who support him in his goal.")
            Text("Genres: Fantasy ")
            Text("More info: www.pokemon.no")
        } // End Column

    } // End Box

} // End AnimeDetailsItem

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun AnimeDetailsItemPreview(){
    AnimeDetailsItem()
}