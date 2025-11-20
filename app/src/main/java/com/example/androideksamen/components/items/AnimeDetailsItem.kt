package com.example.androideksamen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//anime : Anime SKAL TAS MOT SOM PARAMETER
@Composable
fun AnimeDetailsItem() {

    Box(
        modifier = Modifier
            .border(2.dp, Color.Gray)
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {


            Box( // PLACEHOLDER FOR BILDE
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) // End image placeholder box

            Spacer(modifier = Modifier.height(8.dp))

            // Start for rad med tittel og rating boks
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column() {
                    Text(
                        text = "Pokemon",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "ポケモン",
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    )
                }

                // Star rating box

                Box(
                    modifier = Modifier
                        .size(60.dp, 30.dp)
                        .background(
                            color = Color(0xFFfdf1b2),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Icon(Icons.Filled.Star, contentDescription = "none")
                        Text("8.9 ")
                    }
                } // End Star rating box
            } // End row with title and rating box

            // Anime info

            Text("Year: 1996 ")
            Text("Type: TV")
            Text("Number of episodes: 215")
            Text("Synopsis: Pokémon the Series primarily follows Ash Ketchum, a young boy from Pallet Town who dreams of becoming a Pokémon Master. After receiving his first Pokémon, Pikachu, from Professor Oak, Ash embarks on a journey across various regions — including Kanto, Johto, Hoenn, Sinnoh, Unova, Kalos, Alola, and Galar — where he challenges Gym Leaders, competes in regional Pokémon Leagues, or other competitions, and meets a variety of companions who support him in his goal.")
            Text("Genres: Fantasy ")
            Text("More info: www.pokemon.no")
        } // End Column

    } // End Box

} // End AnimeDetailsItem

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimeDetailsItemPreview() {
    AnimeDetailsItem()
}